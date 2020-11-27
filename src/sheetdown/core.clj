(ns sheetdown.core
  (:require [sheetdown.clipboard :as clipboard]
            [sheetdown.html :as html]
            [sheetdown.markdown :as md])
  (:gen-class))

(defn -main
  "Grab the clipboard conents and dump a table"
  [& args]
  (->
   (clipboard/get)
   html/string->table
   md/table->md
   println))
