(ns sheetdown.core
(:require [sheetdown.clipboard :as clipboard])
  (:gen-class))

(defn -main
  "The main function"
  [& args]
(-> clipboard/get println))
