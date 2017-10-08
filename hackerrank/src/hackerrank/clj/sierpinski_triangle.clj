(ns hackerrank.clj.sierpinski-triangle)

(defn bottom-len [h]
  (if (= h 1)
    1
    (+ 2 (bottom-len (dec h)))))

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
    (take height (iter [pos]))))

(defn divide-triangle
  [[x y] h]
  (let [new-h (quot h 2)
        new-bottom-len (bottom-len new-h)
        offset (inc (quot new-bottom-len 2))]
    (reduce concat (concat
                     (triangle [x y] new-h)
                     (triangle [(- x offset) (+ y new-h)] new-h)
                     (triangle [(+ x offset) (+ y new-h)] new-h)))))

(defn draw-triangle-on-area
  [w h coll]
    (doseq [iy (range h)
            ix (range w)]
      (when (zero? ix) (print "\n"))
      (if (some #(= % [ix iy]) coll)
        (print \1)
        (print \_))))

(draw-triangle-on-area 63 32 
                       (divide-triangle [31 0] 32))


