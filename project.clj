(defproject sheetdown "0.1.0-SNAPSHOT"
  :description "Grab Google Sheet range from clipboard and output a markdown table"
  :url "https://github.com/lorin/sheetdown"
  :license {:name "Apache 2.0"
            :url "https://apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.xml "0.0.8"]
                 [clj-tagsoup/clj-tagsoup "0.3.0" :exclusions [org.clojure/clojure org.clojure/data.xml]]]
  :main ^:skip-aot sheetdown.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
