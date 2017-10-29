(ns hackerrank.clj.super-queen-on-chessboard
  (:require [clojure.test :refer :all]))


(defn same-row? [pos1 pos2]
  (= (second pos1) (second pos2)))

(defn same-col? [pos1 pos2]
  (= (first pos1) (first pos2)))

(defn conflict-diagonally? 
  [[x1 y1] [x2 y2]]
  (= 1.0 (Math/abs (double (/ (- y2 y1) (- x2 x1))))))

(defn conflict-in-L? 
  [[x1 y1 :as pos1] pos2]
  (let [ul1 [(- x1 2) (dec y1)]
        ul2 [(dec x1) (- y1 2)]
        dl1 [(- x1 2) (inc y1)]
        dl2 [(dec x1) (+ y1 2)]]
    (or (= ul1 pos2) (= ul2 pos2) (= dl1 pos2) (= dl2 pos2))))


(defn conflict? [pos1 pos2]
  (or (same-col? pos1 pos2)
      (same-row? pos1 pos2)
      (conflict-diagonally? pos1 pos2)
      (conflict-in-L? pos1 pos2)))

(defn queens-patterns [N]
  (letfn [(iter [ix res]
            (if (> ix N)
              res
              (let [tmp (for [iy (range 1 (inc N))]
                          (when (not (some #(conflict? [ix iy] %) res))
                            (iter (inc ix)
                                  (conj res [ix iy]))))
                    tmp2 (filter #(not (nil? %)) tmp)
                    ]
                (if (= ix N)
                  tmp2
                  (apply concat tmp2)))))]
    (iter 1 '())))

(->> (read-line)
     Integer/parseInt
     queens-patterns
     count
     println)

(deftest test-a
  (testing "conflict-in-L?"
    (is (conflict-in-L? [3 3] [2 1]))
    (is (conflict-in-L? [3 3] [1 2]))
    (is (conflict-in-L? [3 3] [1 4]))
    (is (conflict-in-L? [3 3] [2 5]))
    (is (not (conflict-in-L? [1 3] [2 2])))
    )
  (testing "conflict-diagonally?"
    (is (conflict-diagonally? [2 3] [1 2]))
    (is (conflict-diagonally? [2 3] [1 4]))
    (is (not (conflict-diagonally? [1 3] [3 2])))
    )
  (testing "conflic"
    (is (not (conflict? [1 2] [3 5])))
    (is (conflict? [1 2] [1 4]))
    (is (conflict? [4 2] [1 2]))
    ))
