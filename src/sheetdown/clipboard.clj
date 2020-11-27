(ns sheetdown.clipboard
  (:require [clojure.java.io :refer [reader]]
            [clojure.string :refer [join]])
  (:refer-clojure :exclude [get]))


(import java.awt.Toolkit
        java.awt.datatransfer.DataFlavor
        java.io.InputStreamReader)

(defn best-flavor
  "the best flavor for the current clipboard selection"
  []
  (let [clipboard (-> (. Toolkit getDefaultToolkit) .getSystemClipboard)
        flavors (.getAvailableDataFlavors clipboard)]
  (. DataFlavor selectBestTextFlavor flavors )))

(defn is-html?
  "Is the flavor html?"
  [flavor]
  (let [html-flavor (. DataFlavor selectionHtmlFlavor)]
    (.isMimeTypeEqual flavor html-flavor)))

(defn- get-data
  "Return clipboard contents data. Content can potentially be:
   * java.lang.String
   * java.io.InputStringReader

   Throws an exception if the clipboard does not contain html
   "
  []
  (let [clipboard (-> (. Toolkit getDefaultToolkit) .getSystemClipboard)
        flavors (.getAvailableDataFlavors clipboard)
        best-flavor (. DataFlavor selectBestTextFlavor flavors)
        contents (.getData clipboard best-flavor)]
    (if (is-html? best-flavor)
      contents
    (throw (Exception. "Expected clipboard contents to be html, but they are not.
Verify that your clipboard contains a Google Sheets range.")))))


(defn get
  "Retrieve clipboard contents as a string
   
   Throws an exception if the clipboard does not contain html"
  []
  (let [data (get-data)]
    (cond
      (instance? String data) data
      (instance? InputStreamReader data)
      (with-open
       [rdr (reader data)]
        (join (line-seq rdr))))))