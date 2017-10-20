(ns hackerrank.clj.string-reduction)


(defn string-reduction
  [string]
  (letfn [(iter [coll res]
            (if (empty? coll)
              res
              (let [c (first coll)]
                (recur (filter #(not= c %) coll)
                       (conj res c)))))]
    (apply str (iter string []))))

          
(println (string-reduction (read-line)))
