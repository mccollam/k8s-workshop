import os
from flask import Flask

def create_app(config_name=None):
    app = Flask(__name__)
    if config_name is None:
        app.config.from_object(os.environ['APP_SETTINGS'])
    else:
        app.config.from_object(config_name)

    @app.route('/healthz')
    def healthz():
        return 'OK'

    return app

