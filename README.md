orientdb-spring-mvc
===================

An example Spring MVC application using OrientDB's ObjectDatabase.

Why
---

I was trying to use orientdb in my Spring MVC application but there was
no ready to use implementation to handle transactions.
I wanted to have some infrastructure that opens transactions around
my business logic code and handles commits and rollbacks.

If you have any idea how to make the orientdb integration better, or if you find
something ugly in the code, please send me an email or raise a pull request.

Usage
-----
* Checkout
* Start with mvn install jetty:run.
* Add random data by visiting http://localhost:8080/orientdb-spring-mvc/add in your browser.
* View added items on index page: http://localhost:8080/orientdb-spring-mvc/