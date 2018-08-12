;;
;;https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
;;
(ns hackerrank.interview-preparation-kit.arrays.new-year-chaos
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn index-of [coll v]
  (loop [[x & xs] coll
         i 0]
    (cond (> i 2) i
          (= x v) i
          :else (recur xs (inc i)))))

(defn remove-i [i coll]
  (concat (take i coll) (drop (+ 1 i) coll)))

(defn get-bribes [coll1 coll2]
  (loop [[x & xs :as c1] coll1
         [y & ys :as c2] coll2
         cnt 0]
    (cond (nil? x) cnt
          (= x y) (recur xs ys cnt)
          :else (let [iy (index-of c1 y)]
                  (if (> iy 2) 
                    -1
                    (recur (remove-i iy c1)
                           ys
                           (+ cnt iy)))))))

(defn -main []
  (let [t (Integer/parseInt (read-line))]
    (doseq [_ (range t)]
      (let [n (read-line)
            fs (str->nums (read-line))
            sn (range 1 (+ 1 (count fs)))
            bribes (get-bribes sn fs)
            ]
        (println (if (>= bribes 0)
                   bribes
                   "Too chaotic"))))))

(-main)
