;;
;; https://www.hackerrank.com/challenges/area-under-curves-and-volume-of-revolving-a-curv/problem
;;
(ns hackerrank.functional-programming.introduction.area-volume-curve
  (:require [clojure.string :as s]))

(defn make-func [aseq bseq]
  (fn [x]
    (loop [a aseq, b bseq, res 0]
      (if (empty? a)
        res
        (recur (rest a)
               (rest b)
               (+ res (* (first a) 
                         (Math/pow x (first b)))))))))
(defn integra [f dx L R]
  (loop [ci (+ L dx), res 0]
    (if (> ci R)
      (* dx res)
      (recur (+ ci dx)
             (+ res (f ci))))))
         
(defn str->num-array [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

;;
;; read data then evaluate area and volume
;;
(def a-array (str->num-array (read-line)))
(def b-array (str->num-array (read-line)))
(def limit (str->num-array (read-line)))
  
(let [l (first limit)
      r (second limit)
      f (make-func a-array b-array)
      dx 0.001]
  (println (format "%f" (integra f dx l r)))
  (println (format "%f" (* Math/PI (integra #(Math/pow (f %) 2) dx l r)))))

