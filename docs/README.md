
# layout

Layoaut untuk otika ini mempuyai struktur sebagai berikut

## layout:fragment 
1. head-links-css 
1. content
1. libraies-script
1. page-script
1. footer-script

### content

replace 

~~~
<section class="section">
~~~
become
~~~
<section class="section" layout:fragment="content">"
~~~

## replace fragment

1. fragments-layout :: header
1. fragments-layout :: sidebar
1. fragments-layout :: setting-sidebar
1. fragments-layout :: footer


Bagian ini akan diambil
~~~sh

$> cd src/main/resources/static
$> cp index.html ../templates/
$> cd ../templates/ 
$> cp index.html layout.html

~~~



~~~
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
>

~~~

setup layout

Setup layout using index.html
~~~
        <!-- Sidebar -->
        <div th:replace="fragments/mainsidebar :: sidebar"></div>

~~~

page
~~~
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="layout">

~~~

layout.html main contetn

~~~

      <!-- Main Content -->
      <div class="main-content">
        <section class="section" layout:fragment="content">
           <!-- main content -->
        </section>
        <div class="settingSidebar">
        
        
~~~

index.html main content
~~~
      <!-- Main Content -->
      <div class="main-content">
        <section class="section" layout:fragment="content">

~~~