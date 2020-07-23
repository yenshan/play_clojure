(ns ball-fall.core)


(defn get-canvas-context-from-id
  [id]
  (let [canvas (.getElementById js/document id)]
    {:canvas canvas
     :width (.-width canvas)
     :height (.-height canvas)
     :ctx (.getContext canvas "2d")}))

(def canvas-info (get-canvas-context-from-id "canvas"))

(def ball (atom {:color [255 255 255] :pos [100 100]}))


(defn to-color [rgbas]
  (str "rgb("
       (apply str (interpose "," rgbas))
       ")"))


(defn draw-rect [ctx pos size color]
  (let [[x y] pos
        [w h] size]
    (doto ctx
      (aset "fillStyle" (to-color color))
      (.fillRect x y w h))))

(defn draw-obj [ctx {:keys [color pos]}]
  (draw-rect ctx pos [10 10] color))


(defn inc-y [[x y]] [x (inc y)])

(defn clear-screen [cinfo]
  (draw-rect (:ctx cinfo)
             [0 0]
             [(:width cinfo) (:height cinfo)]
             [0 0 0]))

(defn game-loop []
  (let [_blk (update @ball :pos inc-y)]
    (clear-screen canvas-info)
    (draw-obj (:ctx canvas-info) _blk)
    (reset! ball _blk)))
 

(js/setInterval game-loop 30)
