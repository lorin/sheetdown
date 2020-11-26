(ns sheetdown.html
  (:require [pl.danieljanus.tagsoup :as soup]))

(defn- string->stream
"Convert a string to a stream that can be reader"
  [s]
   (-> s
       (.getBytes "UTF-8")
       (java.io.ByteArrayInputStream.)))

(defn parse
  "Parse HTML string"
  [s]
  (-> s string->stream soup/parse))

(defn tree->table-subtree
  "Given a parsed HTML tree, return the sub-tree with the table"
  [tbl]
  (cond
    (nil? tbl) tbl
    (= (soup/tag tbl) :table) tbl)
  :else (first (filter some? (map tree->table-subtree (soup/children tbl)))))


(defn to-table
  "Convert an HTML string to a table.
   
   Right now, it just returns the body of the table tag"
  [s]
  (-> s parse tree->table-subtree)

