(ns hackerrank.algorithms.implementation.picking-numbers
  (:require [clojure.string :as s]))

(defn some-ng-pair? [coll]
  (for [a coll
        b (rest coll)
        :let [dif (Math/abs (- a b))]
        :when (> dif 1)
        ]
    [a b]))

(defn subset [coll]
  (let [dat (map-indexed vector coll)]
    (for [i (range (count dat))]
      (map second
           (remove (fn [[idx _]] (= idx i)) dat)))))

(defn contains-ok-coll? [coll]
  (loop [[d & rst] coll]
    (if (< (count d) 2)
      nil
      (if (empty? (some-ng-pair? d))
        d
        (recur rst)))))

(defn num-of-chosen-integer [coll]
  (if (< (count coll) 2)
    nil
    (let [sset (subset coll)
          res (contains-ok-coll? sset)]
      (if res
        (count res)
        (let [rt (for [e sset
                       :let [r (num-of-chosen-integer e)]
                       :when r
                       ]
                   r)]
          (if (empty? rt)
            nil
            (first (sort #(compare %2 %1) rt))))))))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def _ (read-line))

(->> (str->nums (read-line))
     (num-of-chosen-integer)
     println)
