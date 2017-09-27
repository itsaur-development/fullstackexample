const path = require('path');
const webpack = require("webpack");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const OptimizeCssPlugin = require('optimize-css-assets-webpack-plugin');
const CheckerPlugin = require('awesome-typescript-loader').CheckerPlugin;

module.exports = function (options) {
    return {
        entry: {
            app: ['./src/index.ts']
        },

        output: {
            path: path.join(__dirname, "build"),
            filename: '[name].js'
        },

        devtool: 'source-map',

        resolve: {
            modules: [path.join(__dirname, "node_modules")],
            extensions: ['.js', '.ts', '.sass', '.css']
        },

        module: {
            rules: [
                {
                    test: /\.json$/,
                    loader: 'json-loader'
                },
                {
                    test: /\.ts$/,
                    use: [{
                        loader: 'awesome-typescript-loader',
                        options: {
                            configFileName: path.join(__dirname, 'tsconfig.json')
                        }
                    }]
                },
                {
                    test: /\.tpl.html$/,
                    use: 'ng-cache-loader?prefix=src:**&url'
                },
                {
                    test: /index\.html$/,
                    use: [{
                        loader: 'html-loader'
                    }]
                },
                {
                    test: /^((?!-theme).)*\.sass$/,
                    use: ExtractTextPlugin.extract({
                        fallback: 'style-loader',
                        use: [
                            {
                                loader: 'css-loader',
                                options: {
                                    importLoaders: 1,
                                    minimize: true
                                }
                            },
                            {
                                loader: 'postcss-loader',
                                options: {
                                    plugins: function () {
                                        return [
                                            require('autoprefixer'),
                                            require('postcss-flexboxfixer'),
                                            require('postcss-flexbugs-fixes')
                                        ];
                                    }
                                }
                            },
                            {
                                loader: 'sass-loader'
                            }
                        ]
                    })
                },
                {
                    test: /(-theme)+\.sass$/,
                    use: [
                        {
                            loader: 'css-loader',
                            options: {
                                importLoaders: 1,
                                minimize: true
                            }
                        },
                        {
                            loader: 'postcss-loader',
                            options: {
                                plugins: function () {
                                    return [
                                        require('autoprefixer'),
                                        require('postcss-flexboxfixer'),
                                        require('postcss-flexbugs-fixes')
                                    ];
                                }
                            }
                        },
                        {
                            loader: 'sass-loader'
                        }
                    ]
                },
                {
                    test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                    use: [{
                        loader: 'url-loader',
                        options: {
                            limit: 10000,
                            mimetype: "application/font-woff",
                            name: "[name].[ext]"
                        }
                    }]
                },
                {
                    test: /\.(ttf|eot|svg|png|gif|jpg|mp3)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                    use: [{
                        loader: 'file-loader',
                        options: {
                            name: "[name].[ext]"
                        }
                    }]
                },
                {
                    test: /\.css$/,
                    use: ExtractTextPlugin.extract({
                        fallback: 'style-loader',
                        use: [
                            {
                                loader: 'css-loader',
                                options: {
                                    importLoaders: 1,
                                    minimize: true
                                }
                            },
                            {
                                loader: 'postcss-loader',
                                options: {
                                    plugins: function () {
                                        return [
                                            require('autoprefixer'),
                                            require('postcss-flexboxfixer'),
                                            require('postcss-flexbugs-fixes')
                                        ];
                                    }
                                }
                            }
                        ]
                    })
                }
            ]
        },

        plugins: [
            new ExtractTextPlugin({
                filename: '[name].css'
            }),

            new HtmlWebpackPlugin({
                template: 'src/index.html'
            }),

            new webpack.DefinePlugin({
                BASE_SERVER_URL: JSON.stringify(options.BASE_SERVER_URL)
            }),

            new webpack.ProvidePlugin({
                $: "jquery",
                jQuery: "jquery",
                moment: "moment",
                "window.jQuery": "jquery"
            }),

            new OptimizeCssPlugin(),

            new CheckerPlugin()
        ]
    };
};