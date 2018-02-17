;;
;; https://www.hackerrank.com/challenges/icecream-parlor/problem
;;
(ns hackerrank.algorithms.search.ice-cream-parlor
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(let [t (Integer/parseInt (read-line))]
  (doseq [_ (range t)]
    (let [m (Integer/parseInt (read-line))
          n (Integer/parseInt (read-line))
          costs (str->nums (read-line))
          icosts (map vector costs (range 1 (inc (count costs))))
          pairs (loop [[a & rst] icosts, res []]
                  (if (empty? rst)
                    res
                    (recur rst
                           (concat res (for [i rst
                                             :when (= m (+ (first a) (first i)))
                                             ]
                                         [(second a) (second i)]
                                       )))))
          ]
      (println (s/replace (first pairs) #"[\[\]]" ""))
      )))
  


