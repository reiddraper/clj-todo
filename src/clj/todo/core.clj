(ns todo.core
  (:use
    [compojure.core :as compojure :only [ANY routes]]
    [liberator.core :only [resource]])
  (:require compojure.route))

(defn add-state [handler]
  (fn [request]
    (let [new-request (assoc request :state 5)]
      (handler new-request))))

(def home
  (resource :handle-ok (fn [req] (str "hello, the state is" (get-in req [:request :state])))
            :available-media-types ["text/plain"]))

(def handler (routes (ANY "/" [] (add-state home))
                     (compojure.route/files "/"  {:root "resources/public"})))
