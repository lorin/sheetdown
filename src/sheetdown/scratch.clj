(ns sheetdown.scratch)
(require '[pl.danieljanus.tagsoup :as soup])

(use 'sheetdown.html :reload)

(def html (slurp "simplified-example.html"))

(def t (parse html))

(mapcat soup/children (get-tags t :td))
(mapcat soup/children (get-tags (nth (get-tags t :tr) 1) :td))

(let [rows (get-tags t :tr)]
  (for [row rows] (mapcat soup/children (get-tags row :td))))

(let [rows (get-tags t :tr)]
  (for [row rows] (get-row-values row)))