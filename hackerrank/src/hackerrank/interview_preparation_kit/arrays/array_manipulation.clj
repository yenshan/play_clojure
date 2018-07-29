;;
;;https://www.hackerrank.com/challenges/crush/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
;;

(ns hackerrank.interview-preparation-kit.arrays.array-manipulation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn -update [m i f]
  (if-let [d (get m i)]
    (assoc m i (f d))
    (assoc m i (f 0))))

(let [[n m] (str->nums (read-line))
      ops (for [_ (range m)]
            (str->nums (read-line)))
      summ (reduce (fn [m [s e v]]
                     (-> m
                         (-update s #(+ % v)) 
                         (-update (+ e 1) #(- % v))))
                   {}
                   ops)
      res (loop [i 1, s 0, maxv 0]
            (if (> i (+ n 1))
              maxv
              (if-let [d (get summ i)]
                (recur (inc i)
                       (+ s d)
                       (if (< maxv (+ s d)) (+ s d) maxv))
                (recur (inc i)
                       s
                       maxv))))
      ]
  (println res))

