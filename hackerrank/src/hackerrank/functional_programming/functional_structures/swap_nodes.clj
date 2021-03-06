;;
;; https://www.hackerrank.com/challenges/swap-nodes/problem
;;
(ns hackerrank.functional-programming.functional-structures.swap-nodes
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn ->Node [n l r] {:num n :left l :right r})

(defn append-node [tree i l r]
  (cond (= tree -1) tree
        (= (:num tree) i) (->Node  i
                                  (if (= -1 l) -1 (->Node l -1 -1))
                                  (if (= -1 r) -1 (->Node r -1 -1)))
        :else (->Node (:num tree)
                      (append-node (:left tree) i l r)
                      (append-node (:right tree) i l r))))
(defn swap-node
  ([tree k] (swap-node tree k k 1))
  ([tree k kn depth] (cond (= tree -1) tree
                           (= depth kn) (->Node (:num tree)
                                                (swap-node (:right tree)
                                                           k
                                                           (+ kn k)
                                                           (inc depth))
                                                (swap-node (:left tree)
                                                           k
                                                           (+ kn k)
                                                           (inc depth)))
                           :else (->Node (:num tree)
                                         (swap-node (:left tree)
                                                    k
                                                    kn
                                                    (inc depth))
                                         (swap-node (:right tree)
                                                    k
                                                    kn
                                                    (inc depth))
                                  ))))

(defn inorder-traversal [tree res]
  (when (not= -1 tree)
      (inorder-traversal (:left tree) res)
      (print (:num tree) "")
      (inorder-traversal (:right tree) res)
      ))

(defn print-tree [tree]
  (inorder-traversal tree [])
  (println "")
  )

(let [N (Integer/parseInt (read-line))
      nodes (for [i (range 1 (inc N))]
              (let [[l r] (str->nums (read-line))]
                [i l r]))
      tree (reduce (fn [tr [i l r]]
                     (append-node tr i l r))
                   (->Node 1 -1 -1)
                   nodes)
      T (Integer/parseInt (read-line))
      ks (for [_ (range T)] (Integer/parseInt (read-line)))
      res (reduce (fn [coll k]
                    (cons (swap-node (first coll) k) coll))
                  [tree]
                  ks)
      ]
  (doseq [tr (rest (reverse res))]
    (print-tree tr))
  )
      
  
