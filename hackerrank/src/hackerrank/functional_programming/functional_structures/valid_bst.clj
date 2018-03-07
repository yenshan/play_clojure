;;
;; https://www.hackerrank.com/challenges/valid-bst/problem
;;
(ns hackerrank.functional-programming.functional-structures.valid-bst
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn Node [n l r] {:num n :left l :right r})

(defn add-node [tree n]
  (cond (nil? tree) (Node n nil nil)
        (< n (:num tree)) (Node (:num tree)
                                (add-node (:left tree) n)
                                (:right tree))
        :else (Node (:num tree)
                    (:left tree)
                    (add-node (:right tree) n))))

(defn preorder-traversal [tree]
  (if (nil? tree) []
    (concat [(:num tree)]
            (preorder-traversal (:left tree))
            (preorder-traversal (:right tree)))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [_ (read-line)
          nums (str->nums (read-line))
          tree (reduce add-node nil nums)
          res (= nums (preorder-traversal tree))
          ]
      (println (if res "YES" "NO"))
      )))

