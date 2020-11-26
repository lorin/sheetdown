(ns sheetdown.html-test
  (:require [clojure.test :refer :all]
            [sheetdown.html :refer :all]))

(def fixture "
<meta charset='utf-8'>
  <google-sheets-html-origin>
    <table>
      <colgroup>
        <col width='100' />
        <col width='100' />
      </colgroup>
      <tbody>
        <tr>
          <td>X</td>
          <td>Y</td>
        </tr>
        <tr>
          <td>T</td>
          <td>F</td>
        </tr>
      </tbody>
    </table>
  </google-sheets-html-origin>
</meta>")

(deftest parse-example
  (testing "Parse the example HTML file"
    (let [t (parse fixture)]
      (is (= (first t) :html)))))

;(run-tests)