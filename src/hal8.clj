(require '[datomic.api :as d])

;; we create an in-memory database
(d/create-database "datomic:mem://hal8")

;; we create a connection to that database
(def conn (d/connect "datomic:mem://hal8"))

;; our database schema is a data structure
(def schema [{:db/id (d/tempid :db.part/db )
              :db/ident :author/email
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one
              :db.install/_attribute :db.part/db}
             {:db/id (d/tempid :db.part/db )
              :db/ident :author/name
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one
              :db.install/_attribute :db.part/db}])

;; we deploy our schema to the database using a transaction
;@(d/transact conn schema)

;; this function make adding an author easy
(defn add-author [email name]
  @(d/transact conn [{:db/id (d/tempid :db.part/user )
                      :author/email email
                      :author/name name}]))

;; we add two authors into the database
;(add-author "richhickey@gmail.com" "Rich Hickey")
;(add-author "stuart.halloway@gmail.com" "Stuart Halloway")

;; this function contains a query which returns the email address of a given
;; author
(defn find-email [name]
  (ffirst (d/q '[:find ?email
                 :in $ ?name
                 :where [?e :author/email ?email] [?e :author/name ?name]]
            (d/db conn) name)))

;; we look for email addresses of our authors
;(find-email "Rich Hickey")
;(find-email "Stuart Halloway")
