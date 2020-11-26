(ns sheetdown.core
(:require [sheetdown.clipboard :as clipboard]
          [sheetdown.html :as html])
  (:gen-class))

(defn -main
  "The main function"
  [& args]
(-> "example.html" slurp html/parse println))
