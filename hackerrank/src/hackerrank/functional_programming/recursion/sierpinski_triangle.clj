;;
;; https://www.hackerrank.com/challenges/functions-and-fractals-sierpinski-triangles/problem
;;
(ns hackerrank.functional-programming.recursion.sierpinski-triangle)

(defn next-row [coll]
  (vec 
    (reduce (fn [res [x y]] 
              (conj res 
                    [(dec x) (inc y)]
                    [x (inc y)]
                    [(inc x) (inc y)]))
            #{}
            coll)))

(defn triangle
  "座標posを頂点に高さheightの三角形を作成する。
  三角形を構成する点[x y]のlazy sequenceを返す。"
  [pos height]
  (letfn [(iter [p]
            (cons p (lazy-seq (iter (next-row p)))))]
    (apply concat (take height (iter [pos])))))

(defn bottom-len [h]
  (if (= h 1)
    1
    (+ 2 (bottom-len (dec h)))))

(defn fractal-triangle
  [[x y] h]
  (let [new-h (quot h 2)
        new-bottom-len (bottom-len new-h)
        offset (inc (quot new-bottom-len 2))]
    [(list [x y] new-h)
     (list [(- x offset) (+ y new-h)] new-h)
     (list [(+ x offset) (+ y new-h)] new-h)]))
    
(defn fractal-triangle-n-time [N tri]
  (if (zero? N)
    tri
    (recur (dec N)
           (apply concat (map #(fractal-triangle (first %) (second %)) tri)))))

(defn draw-triangle-on-area
  [w h coll]
    (doseq [iy (range h)
            ix (range w)]
      (when (and (> iy 0) (zero? ix)) (print "\n"))
      (if (some #(= % [ix iy]) coll)
        (print \1)
        (print \_))))

(defn draw-sierpinski-triangle [N]
  (->> (fractal-triangle-n-time N [(list [31 0] 32)])
       (reduce (fn [res d] 
                 (concat res (triangle (first d) (second d))))
               '())
       (draw-triangle-on-area 63 32)))

(draw-sierpinski-triangle (Integer/parseInt (read-line)))
