package com.menyala.sipm;

import com.github.javafaker.Faker;
import com.menyala.sipm.dto.BarangPokok.AddBackOrderDTO;
import com.menyala.sipm.dto.BarangPokok.AddBarangPokokDTO;
import com.menyala.sipm.dto.BarangPokok.AddJenisBarangDTO;
import com.menyala.sipm.dto.Toko.AddMaintenanceDTO;
import com.menyala.sipm.dto.Toko.AddTokoDTO;
import com.menyala.sipm.dto.Toko.AddTransaksiDTO;
import com.menyala.sipm.dto.infrastruktur.AddInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddMaintenanceInfrastrukturDTO;
import com.menyala.sipm.dto.infrastruktur.AddPengecekanInfrastrukturDTO;
import com.menyala.sipm.dto.pasar.CreatePasarDTO;
import com.menyala.sipm.model.*;
import com.menyala.sipm.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SipmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SipmApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(PasarService pasarService, InfrastrukturService infrastrukturService, BarangPokokService barangPokokService, TokoService tokoService) {
		return args -> {
			for (int i = 0; i < 100; i++) {

				var faker = new Faker(new Locale("in-ID"));

				CreatePasarDTO createPasarDTO = new CreatePasarDTO();
				String namaKota = faker.address().cityName();
				String namaJalan = faker.address().streetName();

				String location = Math.random() < 0.5 ? namaKota : namaJalan;

				createPasarDTO.setNama("Pasar " + location);
				createPasarDTO.setRetribusi(retributionGenerator());
				createPasarDTO.setAlamat(faker.address().fullAddress());
				Pasar pasar = pasarService.create(createPasarDTO);


				BarangPokokService barangPokokService1 = new BarangPokokImpl();
				List<String> jenisBarangList = barangPokokService.getJenisBp();

				for (String jenisB : jenisBarangList) {

					AddJenisBarangDTO addJenisBarangDTO = new AddJenisBarangDTO();
					addJenisBarangDTO.setJenis(jenisB);

					JenisBarang jenisBarang = barangPokokService.createJenisBarang(addJenisBarangDTO);
					for (int a = 0; a < 5; a++) {
						AddBarangPokokDTO barangPokok = new AddBarangPokokDTO ();

						barangPokok.setIdJenisBarang(String.valueOf(jenisBarang));
						barangPokok.setNama(jenisB + " " + faker.food().ingredient());
						barangPokok.setStok(faker.number().numberBetween(1, 100)); // Stok antara 1 dan 100
						barangPokok.setTotalPenjual(faker.number().numberBetween(1, 50)); // Total penjual antara 1 dan 50
						barangPokok.setTanggalKadaluwarsa(new Date(System.currentTimeMillis() + faker.number().numberBetween(1, 365) * 24 * 60 * 60 * 1000L)); // Tanggal kadaluarsa dalam 1 tahun ke depan

						List<Toko> listToko = new ArrayList<>();
						List<UUID> listIdToko = new ArrayList<>();
						List<AddTransaksiDTO> listTransaksi = new ArrayList<>();
						List<AddBackOrderDTO> listBackOrder = new ArrayList<>();
						List<AddMaintenanceDTO> listMaintenance = new ArrayList<>();

						for (int z = 0; z < faker.number().numberBetween(1, 5); z++) { // Tambahkan 1-5 toko
							// Buat dan isi objek Toko
							Toko toko = new Toko();
							UUID tokoId = UUID.randomUUID();
							toko.setId(tokoId);
							toko.setNamaToko(faker.company().name());
							toko.setAlamatToko(faker.address().fullAddress());
							toko.setNikPenjual(faker.idNumber().valid());
							toko.setKontakPenjual(faker.phoneNumber().cellPhone());

							// Tambahkan ID toko ke dalam daftar ID
							listIdToko.add(tokoId);
							listToko.add(toko);

							// Tambahkan beberapa entri untuk Transaksi
							for (int u = 0; u < faker.number().numberBetween(2, 5); u++) {
								AddTransaksiDTO transaksi = new AddTransaksiDTO();
								transaksi.setIdToko(tokoId);
								transaksi.setPendapatanHarian(faker.number().numberBetween(1000L, 10000L));
								transaksi.setTanggalTransaksi(new Date());
								listTransaksi.add(transaksi);
							}

							// Tambahkan beberapa entri untuk BackOrder
							for (int p = 0; p < faker.number().numberBetween(2, 5); p++) {
								AddBackOrderDTO backOrder = new AddBackOrderDTO();
								backOrder.setIdToko(tokoId);
								backOrder.setIdPasar(UUID.randomUUID());
								backOrder.setIdJenisBarang(UUID.randomUUID().toString());
								backOrder.setNama(faker.commerce().productName());
								listBackOrder.add(backOrder);
							}

							// Tambahkan beberapa entri untuk Maintenance
							for (int m = 0; m < faker.number().numberBetween(2, 5); m++) {
								AddMaintenanceDTO maintenance = new AddMaintenanceDTO();
								maintenance.setIdToko(tokoId);
								maintenance.setTanggal(new Date());
								maintenance.setDeskripsiMaintenance(faker.lorem().sentence());
								maintenance.setPelakuMaintenance(faker.name().fullName());
								maintenance.setBiayaMaintenance(faker.number().numberBetween(5000L, 20000L));
								listMaintenance.add(maintenance);
							}
						}

						barangPokok.setListIdToko(listIdToko);


						AddJenisBarangDTO jenisBarangDTO = 	new AddJenisBarangDTO();
						// Menyimpan BarangPokok menggunakan service
						barangPokokService.create(barangPokok);
					}}



				List<String> jenisInfrastruktur = infrastrukturService.getJenis();
				for (String jenis : jenisInfrastruktur) {
					// Buat dan isi objek AddInfrastrukturDTO
					AddInfrastrukturDTO addInfrastrukturDTO = new AddInfrastrukturDTO();
					addInfrastrukturDTO.setJenis(jenis);
					addInfrastrukturDTO.setNama(jenis + " " + pasar.getNama());
					addInfrastrukturDTO.setPenanggungJawab(faker.name().fullName());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date fromDate = sdf.parse("2023-01-01");
					Date toDate = sdf.parse("2023-12-31");
					addInfrastrukturDTO.setTanggalPembangunan(faker.date().between(fromDate, toDate));
					addInfrastrukturDTO.setTanggalTerakhirKaliPengecekan(faker.date().between(fromDate, toDate));
					addInfrastrukturDTO.setIdPasar(pasar.getId());

					// Simpan infrastruktur
					infrastrukturService.create(addInfrastrukturDTO);

					// Tambahkan beberapa jadwal pengecekan infrastruktur
					for (int g = 0; g < faker.number().numberBetween(1, 3); g++) { // Misal 1-3 jadwal pengecekan
						AddPengecekanInfrastrukturDTO pengecekanDTO = new AddPengecekanInfrastrukturDTO();
						pengecekanDTO.setInfrastrukturID(addInfrastrukturDTO.getIdPasar()); // Ambil ID infrastruktur
						pengecekanDTO.setTanggal(faker.date().between(fromDate, toDate)); // Tanggal acak
						pengecekanDTO.setBiaya(faker.number().numberBetween(1000L, 5000L)); // Biaya acak
						pengecekanDTO.setPelakuPengecekan(faker.name().fullName()); // Nama pelaku pengecekan
						// Simpan jadwal pengecekan
						infrastrukturService.addPengecekan(pengecekanDTO);
					}

					// Tambahkan beberapa jadwal maintenance infrastruktur
					for (int n = 0; n < faker.number().numberBetween(1, 3); n++) { // Misal 1-3 jadwal maintenance
						AddMaintenanceInfrastrukturDTO maintenanceDTO = new AddMaintenanceInfrastrukturDTO();
						maintenanceDTO.setInfrastrukturID(addInfrastrukturDTO.getIdPasar()); // Ambil ID infrastruktur
						maintenanceDTO.setTanggal(faker.date().between(fromDate, toDate)); // Tanggal acak
						maintenanceDTO.setDeskripsi(faker.lorem().sentence()); // Deskripsi acak
						maintenanceDTO.setPelakuMaintenance(faker.name().fullName()); // Nama pelaku maintenance
						maintenanceDTO.setBiaya(faker.number().numberBetween(2000L, 10000L)); // Biaya acak
						// Simpan jadwal maintenance
						infrastrukturService.addMaintenance(maintenanceDTO);
					}
				}


			}
		};
	}
	private Long retributionGenerator() {
		long min = 1700;
		long max = 2500;
		long step = 100;

		int numberOfSteps = (int) ((max - min) / step+1);

		Random random = new Random();

		int randomIndex = random.nextInt(numberOfSteps);

		return min + (randomIndex * step);
	}
	public static String generateNik(Faker faker) {

		String kodeWilayah = "647401";

		int day = faker.number().numberBetween(1, 31);
		int month = faker.number().numberBetween(1, 12);
		int year = faker.number().numberBetween(1950, 2022);

		boolean isFemale = faker.bool().bool();
		if (isFemale) {
			day += 40;
		}
		String tanggalLahir = String.format("%02d%02d%02d", day, month, year % 100);

		Random random = new Random();
		String nomorUnik = String.format("%04d", random.nextInt(10000));
		return kodeWilayah + tanggalLahir + nomorUnik;
	}

	public static String generatePhoneNumber(Faker faker) {
		// Awalan nomor telepon
		String prefix = "08";

		// Sisa digit (total 10 digit untuk mencapai 12 angka)
		StringBuilder phoneNumber = new StringBuilder(prefix);
		for (int i = 0; i < 10; i++) {
			phoneNumber.append(faker.number().digit());
		}

		return phoneNumber.toString();
	}


}
