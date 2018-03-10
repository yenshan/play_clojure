;;
;; https://www.hackerrank.com/challenges/matrix-rotation/problem
;;
(ns hackerrank.functional-programming.functional-structures.matrix-rotation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn side [f k o mtxm]
  (drop-last 
    (let [elem (filter f mtxm)]
      (condp = o
        :inorder (sort-by k elem)
        :reverse (sort #(compare (get %2 k) (get %1 k)) elem)))))

(defn take-four-sides [rmin rmax cmin cmax mtxm]
  (let [top-e #(and (= rmin (:r %)) (<= cmin (:c %) cmax))
        right-e #(and (= cmax (:c %)) (<= rmin (:r %) rmax))
        bottom-e #(and (= rmax (:r %)) (<= cmin (:c %) cmax))
        left-e #(and (= cmin (:c %)) (<= rmin (:r %) rmax))
        ]
  (concat (side top-e :c :inorder mtxm) 
          (side right-e :r :inorder mtxm)
          (side bottom-e :c :reverse mtxm)
          (side left-e :r :reverse mtxm))))

(defn matrix->sideslist [rows cols mtxm]
  (loop [rmin 0
         rmax (dec rows)
         cmin 0
         cmax (dec cols)
         res []]
    (if (or (>= rmin rmax) (>= cmin cmax))
      res
      (recur (inc rmin)
             (dec rmax)
             (inc cmin)
             (dec cmax)
             (conj res (take-four-sides rmin rmax cmin cmax mtxm))))))

(defn rotate-seq-left [n coll]
  (let [rn (mod n (count coll))]
  (if (zero? rn)
    coll
    (concat (drop rn coll) (take rn coll)))))

(defn update-edge [mtxm coll]
  (map #(assoc %1 :d %2) mtxm coll))

(defn rotate-matrix-left [n rows cols mtxm]
  (let [sidelist (matrix->sideslist rows cols mtxm)
        rot-res (map #(rotate-seq-left n (map :d %)) sidelist)
        ]
    (loop [[x & xs] sidelist
           [y & ys] rot-res
           res {}]
      (if (nil? x)
        res
        (recur xs
               ys
               (reduce (fn [r [a b]]
                         (assoc r [(:r a) (:c a)] b))
                       res
                       (map vector x y)))))))

(defn print-matrix [rows cols mtxm]
  (doseq [r (range rows)
          c (range cols)]
    (print (get mtxm [r c]))
    (if (= c (dec cols))
      (println "")
      (print " "))
    ))

(let [[M N R] (str->nums (read-line))
      mtx-map (apply concat 
                    (for [row (range M)] 
                      (map (fn [[i d]]
                             {:r row :c i :d d })
                           (map-indexed vector (str->nums (read-line))))))
      res (rotate-matrix-left R M N mtx-map)
      ]
  (print-matrix M N res))

