# hal8-datomic

HAL8 Datomic tutorial material.

## Do the following to run the examples

* clone this git repository
* install [leiningen 2](http://leiningen.org)
* change into the directory of the project `hal8-datomic`
* type `lein repl`
* inside the REPL type `(load-file "src/hal8.clj")`
* open the file `src/hal8.clj` in a editor
* start to paste the commented parts one by one into the REPL
  like `@(d/transact conn schema)`
  `(add-author "richhickey@gmail.com" "Rich Hickey")` and so on
