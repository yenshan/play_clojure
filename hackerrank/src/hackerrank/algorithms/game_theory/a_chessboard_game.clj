;;
;; https://www.hackerrank.com/challenges/a-chessboard-game-1/problem
;;
(ns hackerrank.algorithms.game-theory.a-chessboard-game
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(defn move [[x,y]]
  (filter (fn [[x,y]]
            (and (<= 1 x 15) (<= 1 y 15)))
          [[(- x 2) (+ y 1)]
           [(- x 2) (- y 1)]
           [(+ x 1) (- y 2)]
           [(- x 1) (- y 2)]]))

(defn change [plr]
  (if (= plr "First") "Second" "First"))

(def bm (atom {}))

(defn update-table! [tbl pos flg]
  (swap! tbl assoc pos flg)
  flg)

(defn play-game [pos]
  (if-let [d (get @bm pos)]
    d
    (let [next-pos (move pos)]
      (if (empty? next-pos)
        (update-table! bm pos false)
        (let [op (map play-game next-pos)]
          (if (some false? op)
            (update-table! bm pos true)
            (update-table! bm pos false)))))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [p (str->nums (read-line))
          res (play-game p)
          ]
      (println (if res "First" "Second")))))
      
