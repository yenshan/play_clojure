(ns hackerrank.functional-programming.ad-hoc.missing-numbers
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn count-num [nseq]
  (reduce (fn [m d]
            (if (get m d)
              (assoc m d (inc (get m d)))
              (assoc m d 1)))
          {}
          nseq))

(defn print-coll [[n & rst]]
  (if-not (nil? n)
    (do (print n)
        (when-not (empty? rst) (print " "))
        (recur rst))
    (println "")))

(let [_ (read-line)
      nseq1 (str->nums (read-line))
      _ (read-line)
      nseq2 (str->nums (read-line))
      nm1 (count-num nseq1)
      nm2 (count-num nseq2)
      dif-cnt-num (loop [[a & rst] (vec nm2)
                         res []]
                    (if (nil? a)
                      res
                      (let [d (get nm1 (first a))]
                        (recur rst
                               (if (= (second a) d)
                                 res
                                 (conj res (first a)))))))
      ]
  (print-coll (sort dif-cnt-num)))
                             


