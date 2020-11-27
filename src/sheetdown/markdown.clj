(ns sheetdown.markdown
  (:require [clojure.string :refer [join]]))

(defn header [t] (first t))
(defn body [t] (rest t))

(defn dashes
  "Given a string, return a string the same size made up of dashes"
  [s]
  (let [n (count s)]
    (->> "-" (repeat n) join)))

(defn fences
  "Given a collection of strings, return a single string delimited by fences"
  [coll]
  (let [sep "|"
        inner (join sep coll)]
    (str sep inner sep)))

(defn gen-header
  "Generate the markdown representation of the header of the table"
  [t]
  (let [hdr (header t)]
    (str
     (fences hdr)
     "\n"
     (fences (map dashes hdr))
     "\n")))


(defn gen-body
  "Generate a markdown representation of the table body"
  [t]
  (let [rows (body t)]
    (join "\n" (map fences rows))))

(defn table->md
  "Given a table, generate a markdown string representation of the table"
  [t]
  (str
   (gen-header t)
   (gen-body t)))


(def fixture [["X", "Y"], ["T", "F"]])

(gen-header fixture)