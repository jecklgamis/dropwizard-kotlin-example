#!/usr/bin/env python3

import argparse
import os

from jinja2 import Template


def write_deployment_def(template, output, name, version):
    print(f"Creating deployment definition app_name={name}, version={version}")
    template = Template(open(template, 'rt').read())
    content = template.render(name=name, version=version)
    open(output, 'wt').write(content)
    print(f"Wrote {os.path.abspath(output)}")


def parse_args():
    parser = argparse.ArgumentParser(description='Create Kubernetes definition file')
    parser.add_argument('--version',
                        default='latest',
                        help='Version')
    return parser.parse_args()


args = parse_args()
write_deployment_def('deployment-template.yaml', f"deployment-{args.version}.yaml", "dropwizard-kotlin-example",
                     args.version)
