(ns hackerrank.algorithms.implementation.absolute-permutation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn cand-permutation [n k]
  (->> (range 1 (inc n))
       (map (fn [d]
              (let [n1 (+ k d)
                    n2 (- d k)]
                [(if (<= 1 n1 n) n1 nil) 
                 (if (<= 1 n2 n) n2 nil)]
                )))))

(defn permutations [coll]
  (letfn [(iter [[a & rst], i, res]
            (if (empty? a)
              res
              (let [ret (for [e a 
                              :let [pd (get res e)]
                              :when (and e (not pd))]
                          (iter rst (inc i) (assoc res e i)))]
                (if (empty? rst)
                  ret
                  (apply concat ret)))))
          ]
    (iter coll 0 {})))

(defn print-coll [coll]
  (loop [[a & rst] coll]
    (if (nil? a)
      (println "")
      (do (print a)
          (when-not (empty? rst)
            (print " "))
          (recur rst)
            ))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [[n k] (str->nums (read-line))
          dat (cand-permutation n k)
          pmt (->> (permutations dat)
                   (filter #(= n (count %)))
                   (map #(sort-by second %))
                   set
                   (map #(vec (map first %)))
                   sort
                   )
          ]
      (if (empty? pmt)
        (println -1)
        (print-coll (first pmt)))
      )))
            
