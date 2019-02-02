import os
import signalfx
from flask import Flask

def create_app(config_name=None):
    app = Flask(__name__)
    if config_name is None:
        app.config.from_object(os.environ['APP_SETTINGS'])
    else:
        app.config.from_object(config_name)

    token = os.environ['SFX_TOKEN']

    @app.route('/healthz')
    def healthz():
        with signalfx.SignalFx().ingest(token) as sfx:
            sfx.send(
                    counters=[
                        {'metric': 'k8sws.flask.path.healthz.calls',
                            'value': 1,
                            'timestamp': int(time.now()),
                            'dimensions': {
                                'app': 'k8ws-python-flask'},
                            },
                        ],
                    cumulative_counters=[
                        {'metric': 'k8sws.flask.path.heathz.calls_cumulative',
                            'value': 1,
                            'timestamp': int(time.now()),
                            'dimensions': {
                                'app': 'k8ws-python-flask'},
                            },
                        ])
        return 'OK'

    return app

