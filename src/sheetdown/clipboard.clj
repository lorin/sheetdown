(ns sheetdown.clipboard
  (:require [clojure.java.io :refer [reader]]
            [clojure.string :refer [join]])
  (:refer-clojure :exclude [get]))


(import java.awt.Toolkit
        java.awt.datatransfer.DataFlavor
        java.io.InputStreamReader)


(defn- get-data
  "Return clipboard contents data. Can potentially be
   * java.lang.String
   * java.io.InputStringReader
   "
  []
  (let [clipboard (-> (. Toolkit getDefaultToolkit) .getSystemClipboard)
        flavors (.getAvailableDataFlavors clipboard)
        best-flavor (. DataFlavor selectBestTextFlavor flavors)]
    (.getData clipboard best-flavor)))

(defn get
  "Retrieve clipboard contents"
  []
  (let [data (get-data)]
    (cond
      (instance? String data) data
      (instance? InputStreamReader data) (with-open
                                          [rdr (reader data)]
                                           (join (line-seq rdr))))))