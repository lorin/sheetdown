(ns sheetdown.core
  (:require [sheetdown.clipboard :as clipboard]
            [sheetdown.html :as html]
            [sheetdown.markdown :refer [table->md]])
  (:gen-class))

(defn -main
  "Grab the clipboard conents and dump a table"
  [& args]
  (->
   (clipboard/get)
   html/string->table
   table->md
   println))
