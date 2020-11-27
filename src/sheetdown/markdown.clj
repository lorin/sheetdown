(ns sheetdown.markdown
  (:require [clojure.string :refer [join]]))

(defn header [t] (first t))
(defn body [t] (rest t))

(defn pad [s n]
  "Left pad a string with spaces so it fits in a width of n"
  (let [fmt (str "%" n "s")]
    (format fmt s)))

(defn transpose
  "Transpose a table"
  [tbl]
  (apply map vector tbl))

(defn max-len
  "Given a collection of strings, return the length of the largest"
  [coll]
  (let [lengths (map count coll)]
    (reduce max lengths)))

(defn max-column-widths
  [tbl]
  (let [columns (transpose tbl)]
    (map max-len columns)))


(defn dashes
  "Given a string, return a string the same size made up of dashes"
  [s]
  (let [n (count s)]
    (->> "-" (repeat n) join)))


(defn fences
  "Given a collection of strings, return a single string delimited by fences"
  ([coll]
   (let [sep "|"
         inner (join sep coll)]
     (str sep inner sep)))
  ([coll widths]
   (let [string-widths (map vector coll widths)
         padded (for [[s w] string-widths] (pad s w))]
     (fences padded))))

(defn gen-header
  "Generate the markdown representation of the header of the table"
  [t widths]
  (let [hdr (header t)
        dash #(->> "-" (repeat %) join)]
    (str
     (fences hdr widths)
     "\n"
     (fences (map dash widths))
     "\n")))


(defn gen-body
  "Generate a markdown representation of the table body"
  [t widths]
  (let [rows (body t)
        f #(fences % widths)]
    (join "\n" (map f rows))))

(defn table->md
  "Given a table, generate a markdown string representation of the table"
  [t]
  (let [widths (max-column-widths t)]
    (str
     (gen-header t widths)
     (gen-body t widths))))
