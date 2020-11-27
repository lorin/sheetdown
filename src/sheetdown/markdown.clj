(ns sheetdown.markdown)

(defn header [t] (first t))
(defn body [t] (rest t))

(defn gen-header
  "Generate the markdown representation of the header of the table"
  [t] 
; TODO: Actually do the logic here
  (header t))


(defn gen-body
  [t]
  (body t))

(defn table->md
  "Given a table, generate a markdown string representation of the table"
  [t]
  (str 
   (gen-header t)
   (gen-body t)))
