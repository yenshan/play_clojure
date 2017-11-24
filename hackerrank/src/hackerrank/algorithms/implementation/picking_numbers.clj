(ns hackerrank.algorithms.implementation.picking-numbers
  (:require [clojure.string :as s]))

(defn uniq-combination [coll]
  (loop [[a & rst] coll, res #{}]
    (if (empty? rst)
      (vec res)
      (recur rst 
             (apply conj res 
                    (for [t rst]
                      (sort [a t])))))))

(defn some-ng-pair? [coll]
  (->> (map (fn [[a b]] (- a b)) (uniq-combination coll))
       (filter #(or (< % -1) (> % 1)))))

(defn contains-ok-coll? [coll]
  (loop [[d & rst] coll]
    (if (< (count d) 2)
      nil
      (if (empty? (some-ng-pair? d))
        d
        (recur rst)))))


(defn subset [coll]
  (let [dat (map-indexed vector coll)]
    (->> (range (count dat))
         (reduce (fn [res i]
                   (conj res
                         (->> dat
                              (remove (fn [[idx _]] (= idx i)))
                              (map second)
                              sort)))
                 #{})
         vec)))

(defn map-subset [coll]
  (->> coll
       (reduce (fn [res e]
                 (apply conj res (subset e)))
               #{})
       vec))

;(def dat [6 5 3 3 1])

;(map-subset (map-subset [dat]))

(defn num-of-chosen-integer [coll]
  (loop [[e & rst :as sset] [coll]]
    (if (< (count e) 2)
      0
      (if (contains-ok-coll? sset)
        (count e)
        (recur (map-subset sset))))))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def _ (read-line))

(->> (read-line)
     str->nums
     num-of-chosen-integer
     println)
