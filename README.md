# sipm firebaseTest branch

11 januari 2025, inisialisasi dan konfigurasi -firebase init.
  - "file aplication.properties" dimasukkan token2 yang merupakan identitas dari server firebase storage
  - SDK yang digunakan : Admin SDK (terdapat pada dependencies build.gradle)
  - tidak memerlukan rules, karena admin SDK memiliki feature akses bebas ke bagian backed firebase
  - file yang auto generate :
      1. firebaserc
      2. firebase.json
      3. storage.rules (tidak perlu dihiraukan)
