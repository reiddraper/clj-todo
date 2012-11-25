(ns todo.core
  (:use
    [compojure.core :as compojure :only [ANY routes]]
    [liberator.core :only [resource]])
  (:require compojure.route))

(def home
  (resource :handle-ok "welcome home!"
            :available-media-types ["text/plain"]))

(def handler (routes (ANY "/" [] home)
                     (compojure.route/files "/"  {:root "resources/public"})))
