(ns hackerrank.clj.filter-elements
  (:require [clojure.string :as s]))

(defn count-number-in-seq [coll]
  "generate map of {number [index count]} from coll which is sequence of numbers."
   (reduce (fn [m d]
            (let [k (first d)
                  pre-d (get m k)
                  new-d (if pre-d
                          (update pre-d 1 inc)
                          [(second d) 1])]
              (assoc m k new-d)))
          {}
          coll))

(defn filter-elements
  [n coll]
  (->> (map (fn [a b] [a b]) coll (range (count coll)))
       count-number-in-seq
       seq
       (filter (fn [[_ [_ cnt]]] (>= cnt n)))
       (sort-by (fn [[_ [no _]]] no)) 
       (map (fn [[a _]] a))
       ))

(defn string->num-vec [string]
  (->> (s/split string #" ")
       (map #(Integer/parseInt %))))

(defn print-seq [coll]
  (if (empty? coll)
    (print "-1")
    (doall
      (loop [c coll]
        (print (first c))
        (when (next c)
          (print " ")
          (recur (rest c))))))
  (print "\n"))

(def T (Integer/parseInt (read-line)))

(doseq [_ (range T)]
  (let [[_ n] (string->num-vec (read-line))]
    (print-seq (filter-elements n (string->num-vec (read-line))))))

