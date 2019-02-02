import io
from setuptools import find_packages, setup

with io.open('Readme.md', 'rt', encoding='utf8') as f:
    readme = f.read()

setup(
    name='sfx',
    version='0.1.0',
    license='Apache-2.0',
    maintainer='Brian Ashburn',
    maintainer_email='bashburn@signalfx.com',
    long_description=readme,
    packages=find_packages(),
    include_package_data=True,
    zip_safe=False,
    install_requires=[
        'flask',
        'signalfx',
    ],
    extra_requires={
        'test': [
            'pytest',
            'coverage',
        ],
    },
)
