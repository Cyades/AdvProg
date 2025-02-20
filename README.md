# 🛒 AdvProg Eshop
**Nama:** Malvin Scafi  
**NPM:** 2306152430  
**Kelas:** Pemrograman Lanjut A

---

## 📌 Riwayat Modul
| **Modul** | **Tautan**                   |
|-----------|------------------------------|
| Modul 1   | [Lihat Modul 1](#📘-modul-1) |
| Modul 2   | [Lihat Modul 2](#📘-modul-2) |
---

## 📘 Modul 1
### ✨ Reflection 1
Berikut adalah refkelsi saya terhadap Clean Code Principles dan Secure Coding Practices
- Clean Code Principles Implemented :
    - Single Responsibility Principle : Setiap fungsi memiliki tanggung jawab tunggal yang jelas
    - Meaningful Names : Variabel memiliki nama yang jelas dan deskriptif
    - Comments : Saya tidak menambahkan komentar yang tidak dibutuhkan

Tetapi, saya sadar bahwa saya masih belum mengimplementasikan hal seperti error handling dan juga penanganan nilai null, yang rencananya akan menjadi fokus perbaikan di modul berikutnya. Secure Coding pada code saya kali ini juga masih ada yang belum saya terapkan seperti belum adanya autentikasi dan otorisasi, lalu masih belum ada validasi input serta encoding untuk output jadi code saya ini masih rentan akan serangan.


### ✨ Reflection 2
1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

    - Setelah membuat unit test saya merasa kode yang telah saya buat ini lebih aman karena jika saya menambahkan sesuatu pada kode saya, saya dapat mengtes dengan unit test yang sudah ada sebelumnya agar memastikan hal yang saya tambahkan tidak menyebabkan error atau bug baru pada kode yang sudah ada sebelumnya.
    - Berapa unit test yang harus dibuat?, menurut saya jumlahnya tidak pasti, tetapi setiap test yang dibuat harus memiliki tujuan/fungsi yang jelas seperi, menguji perilaku normal/yang diharapkan atau menguji kasus kasus khusus.
    - Menurut saya untuk Code Coverage dan keterbatasannya, 100% code coverage tidak menjamin kode bebas dari bug karena coverage hanya menunjukkan baris mana yang dieksekusi, bukan apakah logika code benar. Sebagai Contoh pada ProductTest.java, meskipun memiliki 100% coverage, akan tetapi ini tidak menguji input yang tidak valid.

2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. 
What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! Please write your reflection inside the repository's README.md file.

    - Menurut saya, Pembuatan kelas java baru hanya untuk menguji jumlah produk dalam Product List dengan menggunakan setup dan instance variables yang identik dengan CreateProductFunctionalTest.java menyebabkan masalah duplikasi yang tidak perlu. Hal ini juga bertentangan dengan prinsip clean code terutama DRY (Don't Repeat Yourself), karena seharusnya pengujian ini dapat diintegrasikan kedalam test suite yang sudah ada.
        - Ketika pengujian ini dipisahkan juga membuat kode menjadi lebih sulit dipahami, developer lain yang membaca bisa saja kebingungan dengan pemisahan test unit tanpa alasan yang jelas ini, dan jadi harus beralih antara beberapa file untuk memahami pengujian yang sebenarnya terkait dengan fitur yang sama.
        - Dengan dua file terpisah untuk pengujian fitur yang sama juga menciptakan tantangan dalam pemeliharaan kode. Jika perlu dilakukan perubahan pada setup atau struktur pengujian, maka hal ini harus dilakukan modifikasi pada dua lokasi berbeda, hal ini tidak hanya membuang waktu akan tetapi juga meningkatkan resiko akan terjadinya inkonsistensi.
        - Hal yang sebaiknya dilakukan adalah menambahkan pengujian jumlah produk kedalam CreateProductFunctionalTest.java. Dengan ini, kita dapat mengurangi duplikasi kode dan membuat kode lebih mudah dipelihara.
---

## 📘 Modul 2
### ✨ Reflection
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.


2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!


---