(ns todo.core
  (:use
    [compojure.core :as compojure :only [ANY routes]]
    [liberator.core :only [resource]])
  (:require compojure.route
            [todo.list-resource :as list-resource]))

(defn- middleware-helper
  [req-altering-fun]
  (fn [handler]
    (fn [req]
      (handler (req-altering-fun req)))))

(def add-state
  (middleware-helper #(assoc % :state 5)))

(defn route-var-helper
  [k v]
  (middleware-helper #(assoc % k v)))

(def home
  (resource :handle-ok (fn [req]
                         (str "hello, the state is "
                              (get-in req [:request :state])))
            :available-media-types ["text/plain"]))

(defn list-handler
  [list-id]
  ((route-var-helper :list-id list-id) list-resource/resource))

(def handler (routes (ANY "/" [] (add-state home))
                     (ANY "/list/:id" [id] (list-handler id))
                     (compojure.route/files "/"  {:root "resources/public"})))
