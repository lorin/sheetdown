(ns sheetdown.html
  (:require [pl.danieljanus.tagsoup :as soup]
            [clojure.string :refer [trim]]))

(defn- string->stream
"Convert a string to a stream that can be reader"
  [s]
   (-> s
       (.getBytes "UTF-8")
       (java.io.ByteArrayInputStream.)))

(defn parse
  "Parse HTML string into a tagsoup tree"
  [s]
  (-> s string->stream soup/parse))

(defn get-tags
  "Given a parsed HTML tree, return the sub-trees with matching tag
   
   Stops at the top of a tree, so if the parent matches, won't check children"
  [tbl tag]
  (cond
    (nil? tbl) []
    (string? tbl) [] ; a string means the children were text rather than nodes. Stop here.
    (= (soup/tag tbl) tag) [tbl]
    :else (let [f #(get-tags % tag)]
            (->> tbl soup/children (mapcat f)))))

(defn get-tag
  "Get a single sub-tree that matches a tag"
  [tbl tag]
  (first (get-tags tbl tag)))

(defn get-row-values
  "Given a tr subtree, return the values in the td cells"
  [row]
  (vec (map trim
            (mapcat soup/children (get-tags row :td)))))

(defn string->table
  "Convert an HTML string to our internal table representation"
  [s] 
  (let [t (parse s)
        tbl (get-tag t :table)
        rows (get-tags tbl :tr)]
    (vec (for [row rows]
           (get-row-values row)))))