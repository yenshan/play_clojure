(ns hackerrank.clj.filter-elements
  (:require [clojure.string :as s]))


(defn pick-up-dat [dat coll]
  (letfn [(iter [coll res1 res2]
            (if (empty? coll)
              (list res1 res2)
              (if (= dat (first coll))
                (recur (rest coll)
                       (conj res1 dat)
                       res2)
                (recur (rest coll)
                       res1
                       (conj res2 (first coll))))))]
    (iter coll [] [])))

(defn pick-up-all-seq-num [coll]
  (let [[res remain] (pick-up-dat (first coll) coll)]
    (if (or (empty? res) (= (count remain) 1))
      (list res remain)
      (cons res (pick-up-all-seq-num remain)))))

(defn filter-elements [n coll]
  (->> (pick-up-all-seq-num coll)
       (filter #(>= (count %) n))
       (map #(first %))))

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

