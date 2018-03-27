(ns prog-math-puzzle.q08)

(defn up [[x y]] [x (dec y)])
(defn down [[x y]] [x (inc y)])
(defn left [[x y]] [(dec x) y])
(defn right [[x y]] [(inc x) y])

        
(defn move-robot [pos n]
  (letfn [(move [coll cnt]
            (if (= cnt n)
              1
              (let [pos (first coll)]
                (reduce +
                        (for [p [(up pos) (down pos) (left pos) (right pos)]
                              :when (not-any? #(= p %) (rest coll))
                              ]
                          (move (cons p coll) (inc cnt)))))))
          ]
    (move [pos] 0)))
 
(println (move-robot [0 0] 12))
        
