(ns todo.core
  (:use
    [compojure.core :as compojure :only [ANY routes]]
    [liberator.core :only [resource]])
  (:require compojure.route
            [todo.home-resource :as home-resource]
            [todo.list-resource :as list-resource]
            [todo.lists-resource :as lists-resource]
            [todo.model :as model]))

(defn- middleware-helper
  [req-altering-fun]
  (fn [handler]
    (fn [req]
      (handler (req-altering-fun req)))))

(defn add-state
  [state]
  (middleware-helper #(assoc % :state state)))

(defn route-var-helper
  [k v]
  (middleware-helper #(assoc % k v)))

(defn list-handler
  [list-id]
  ((route-var-helper :list-id list-id) list-resource/resource))

(defn create-handler
  []
  (let [state (model/create-list-state)
        req-with-state (add-state state)]
    (routes (ANY "/" [] (req-with-state home-resource/resource))
            (ANY "/lists" [] (req-with-state lists-resource/resource))
            (ANY "/lists/:id" [id] (req-with-state (list-handler id)))
            (compojure.route/files "/"  {:root "resources/public"}))))

(def handler (create-handler))
