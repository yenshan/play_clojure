;;
;; https://www.hackerrank.com/challenges/electronics-shop/problem
;;
(ns hackerrank.algorithms.implementation.electronic-shop
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn print-result [n]
  (if n
    (println n)
    (println -1)))

(let [[money _ _] (str->nums (read-line))
      keyboards (str->nums (read-line))
      usb-devices (str->nums (read-line))]
  (->> (for [k keyboards
             u usb-devices]
         (+ k u))
       (sort #(compare %2 %1))
       (some #(when (<= % money) %))
       print-result))

