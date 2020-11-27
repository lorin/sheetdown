(ns sheetdown.core
  (:require [sheetdown.clipboard :as clipboard]
            [sheetdown.html :as html]
            [sheetdown.markdown :refer [table->md]])
  (:gen-class))

(defn -main
  "Grab the clipboard contents and dump a markdown table"
  [& args]
  (try 
    (->
     (clipboard/get)
     html/string->table
     table->md
     println)
    (catch Exception e
      (binding [*out* *err*]
        (println "Error:" (.getMessage e))
        (System/exit 1)))))
