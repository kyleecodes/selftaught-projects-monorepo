# Get Listening History
A Flask app that will obtain your listening history from Spotify deployed to AWS Lambda using the Zappa serverless framework!

## Try it yourself!
*This documentation is deploying a Flask app that already exists without having to change it.*
First, clone the this repo.

### Before developing:
You will need a Spotify Developer account with a registered app. From this, save your:
- `client_id`
- `client_secret`
in a `secrets.py` file hidden in your `.gitignore`

You will also need an AWS account with basic knowledge of IAM roles.  Create or obtain an access key in the Security Credientials console, and obtain:
- `aws_access_key_id`
- `aws_secret_access_key`
- As well as your `region` and desired `output` (I used `json`)

Save these variables in a file titled `config` within a directory called `.aws` (so path is `<root directory>\.aws\config`)

       [default]
       aws_access_key_id = 
       aws_secret_access_key = 
       output=
       region=

`[default]` will be the name of your Zappa project, but for now, just name it "default."

### Now start coding...
First thing you will want to do is create a new directory, cd into it, and create then activate your virtual environment (venv) environment according to your operating system.

- install dependencies, `pip install flask spotipy zappa cryptography` and any other modules you decide to use!

- run `zappa init` to initialize a zappa project. You may choose the default options.

- run `zappa deploy default` (if default is your profile name, otherwise, replace with your profile name listed in zappa-settings.json.)

Your project will be live at a URL such as `https://5ihaocijcf.execute-api.<region>.amazonaws.com/<profile_name>`

Run `zappa update default` to update changes
Run `zappa status` and `zappa tail` for debugging.

You should now be able to see your function in the AWS Lambda console as well!


