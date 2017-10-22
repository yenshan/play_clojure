(ns hackerrank.clj.filter-elements-2
  (:require [clojure.string :as s]))


(defn pick-up-dat [dat coll]
  (letfn [(iter [coll cnt res2]
            (if (empty? coll)
              (list [dat cnt] res2)
              (if (= dat (first coll))
                (recur (rest coll)
                       (inc cnt)
                       res2)
                (recur (rest coll)
                       cnt
                       (conj res2 (first coll))))))]
    (iter coll 0 [])))

(defn filter-elements [n coll]
  (letfn [(iter [coll res]
            (let [[[dat cnt] remain] (pick-up-dat (first coll) coll)]
              (if (zero? cnt)
                res
                (recur remain 
                       (if (>= cnt n)
                         (conj res dat)
                         res)))))]
    (iter coll [])))

(defn string->num-vec [string]
  (->> (s/split string #" ")
       (map #(Integer/parseInt %))))

(defn print-seq [coll]
  (if (empty? coll)
    (print "-1")
    (doseq [e coll]
      (print e "")))
  (print "\n"))

(def T (Integer/parseInt (read-line)))

(doseq [_ (range T)]
  (let [[_ n] (string->num-vec (read-line))]
    (print-seq (filter-elements n (string->num-vec (read-line))))))

