package saaransh;

class learner
{
	//double theta[][] = new double[4][1];
	double hypo[][];
	double cost[][];
	double learning_rate = 0.1;//old_vale=0.1;
	double iterations = 1000000;
	
	// Sigmoid Function for Hypothesis
	
	public double[][] sigmoid(double[][] feature,double[][] weights)
	{
		double[][] z = new double[feature.length][weights[0].length];
		double sum = 0;
		for(int i=0;i<feature.length;i++)
		{
			for(int j=0;j<weights[0].length;j++)
			{
				for(int k=0;k<weights.length;k++)
				{
					sum = sum + feature[i][k] * weights[k][j];
				}
				z[i][j] = sum;
				sum=0;
			}
		}
		hypo = new double[z.length][z[0].length];
		for(int i=0;i<z.length;i++)
		{
			for(int j=0;j<z[0].length;j++)
			{
				hypo[i][j] = (1/( 1+( Math.exp( (-1)*z[i][j] ) ) ) );
			}
		}
		return hypo;
	}
	
	// Cost Function J
	
	public double[][] cost(double[][] feature_value,double[][] parameters,double[][] y)
	{
		double sum=0;
		double[][] transpose_y = new double[y[0].length][y.length];
		for(int i=0;i<y.length;i++)
		{
			for(int j=0;j<y[0].length;j++)
			{
				transpose_y[j][i] = (-1)*y[i][j];
			}
		}
		double[][] transpose_y_minus = new double[y[0].length][y.length];
		for(int i=0;i<y.length;i++)
		{
			for(int j=0;j<y[0].length;j++)
			{
				transpose_y_minus[j][i] = (1-y[i][j]);
			}
		}
		double[][] hypothe = new double[feature_value.length][1];
		hypothe = sigmoid(feature_value,parameters);
		double[][] log_hypothe = new double[feature_value.length][1];
		for(int i=0;i<hypothe.length;i++)
		{
			for(int j=0;j<hypothe[0].length;j++)
			{
				log_hypothe[i][j] = Math.log(hypothe[i][j]);
			}
		}
		double[][] log_hypothe_minus = new double[feature_value.length][1];
		for(int i=0;i<log_hypothe_minus.length;i++)
		{
			for(int j=0;j<log_hypothe_minus[0].length;j++)
			{
				log_hypothe_minus[i][j] = Math.log(1-hypothe[i][j]);
			}
		}
		double[][] a = new double[1][1];
		double[][] b = new double[1][1];
		for(int i=0;i<transpose_y.length;i++)
		{
			for(int j=0;j<log_hypothe[0].length;j++)
			{
				for(int k=0;k<log_hypothe.length;k++)
				{
					sum = sum + transpose_y[i][k] * log_hypothe[k][j];
				}
				a[i][j] = sum;
				sum=0;
			}
		}
		for(int i=0;i<transpose_y_minus.length;i++)
		{
			for(int j=0;j<log_hypothe_minus[0].length;j++)
			{
				for(int k=0;k<log_hypothe_minus.length;k++)
				{
					sum = sum + transpose_y_minus[i][k] * log_hypothe_minus[k][j];
				}
				b[i][j] = sum;
				sum=0;
			}
		}
		cost = new double[1][1];
		for(int i=0;i<cost.length;i++)
		{
			for(int j=0;j<cost[0].length;j++)
			{
				cost[i][j] = ((a[i][j]-b[i][j])/feature_value.length);
			}
		}
		
		/*
		for(int i=0;i<cost.length;i++)
		{
			for(int j=0;j<cost[0].length;j++)
			{
			System.out.println(cost[i][j]);
			}
		}
		*/
		return cost;
	}
	
	// Gradient Descent for parameter optimization
	
	//Gradient Calculation
	public double[][] gradient(double[][] feature_val, double[][] theta,double[][] prediction)
	{
		double sum=0;
		double[][] hypothesis = new double[feature_val.length][theta[0].length];
		double[][] hypo_minus_predict = new double[hypothesis.length][hypothesis[0].length];
		double[][] feature_val_transpose = new double[feature_val[0].length][feature_val.length];
		double[][] secondterm_product = new double[theta.length][1];
		// double[][] grad = new double[theta.length][1];
				hypothesis = sigmoid(feature_val,theta);
		
		for(int i=0;i<hypothesis.length;i++)
		{
			for(int j=0;j<hypothesis[0].length;j++)
			{
				hypo_minus_predict[i][j] = (hypothesis[i][j] - prediction[i][j]); 
			}
		}
		
		for(int i=0;i<feature_val.length;i++)
		{
			for(int j=0;j<feature_val[0].length;j++)
			{
				feature_val_transpose[j][i] = feature_val[i][j];
			}
		}
		
		/*
		double[][] secondterm_minus = new double[feature_val.length][1];
		for(int i=0;i<feature_val.length;i++)
		{
			for(int j=0;j<feature_val[0].length;j++)
			{
				secondterm_minus[i][j] = hypothesis[i][j]-prediction[i][j];
			}
		}
		*/
		
		
		for(int i=0;i<feature_val_transpose.length;i++)
		{
			for(int j=0;j<hypo_minus_predict[0].length;j++)
			{
				for(int k=0;k<hypo_minus_predict.length;k++)
				{
					sum = sum + feature_val_transpose[i][k] * hypo_minus_predict[k][j];
				}
				secondterm_product[i][j] = sum;
				sum=0;
			}
		}
		
		/*
		double[][] cost_function = new double[1][1];
		
		for(int k=0;k<iterations;k++)
		{
			for(int i=0;i<theta.length;i++)
			{
				for(int j=0;j<theta[0].length;j++)
				{
					theta[i][j] = theta[i][j] - (((learning_rate/feature_val.length) * secondterm_product[i][j]));
				}
			}
		*/
			
			/*
			for(int i=0;i<1;i++)
			{
				for(int j=0;j<1;j++)
				{
					cost_function = cost(feature_val,theta,prediction);
					System.out.println(cost_function[i][j]);
				}
			}
			*/
		
		
		return secondterm_product;
}

	
	//Parameter Update
	
	public double[][] parameter_update(double[][] features_val,double[][] parameter_val,double[][] label)
	{
		// double[][] updated_theta = new double[parameter_val.length][parameter_val[0].length];
		double[][] secondpart = new double[parameter_val.length][parameter_val[0].length];
		double[][] cost_function = new double[label[0].length][features_val[0].length];
		for(int k=0;k<iterations;k++)
		{
			secondpart = gradient(features_val,parameter_val,label);
			for(int i=0;i<parameter_val.length;i++)
			{
				for(int j=0;j<parameter_val[0].length;j++)
				{
					parameter_val[i][j] = parameter_val[i][j] - (((learning_rate/features_val.length) * secondpart[i][j]));
				}
			}
			
			
			for(int i=0;i<1;i++)
			{
				for(int j=0;j<1;j++)
				{
					cost_function = cost(features_val,parameter_val,label);
					System.out.println(cost_function[i][j]);
				}
			}
			
			
	
		}
		
		return parameter_val;
	}
	
	//Classifier
	public double[][] classify(double[][] feature_values,double[][] weights)
	{
		double[][] predicted_value = new double[feature_values.length][weights[0].length];
		predicted_value = sigmoid(feature_values,weights);
		return predicted_value;
	}
}

public class Model{
	public static void main(String args[])
	{
		double features[][] = {
				/*
				{1.0,0.55042017,1.0,0.30769232},
				{1.0,0.85294116,0.95238096,0.35384616},
				{1.0,0.15126051,0.9047619,0.07692308},
				{1.0,1.0,0.85714287,1.0},
				{1.0,0.6134454,0.8095238,0.5538462},
				{1.0,0.20588236,0.7619048,0.06153846},
				{1.0,0.13865547,0.71428573,0.13846155},
				{1.0,0.6344538,0.6666667,0.07692308},
				{1.0,0.13445379,0.61904764,0.06153846},
				{1.0,0.34033614,0.5714286,0.12307692},
				{1.0,0.13025211,0.52380955,0.16923077},
				{1.0,0.24369748,0.47619048,0.2},
				{1.0,0.071428575,0.42857143,0.06153846},
				{1.0,0.13865547,0.3809524,0.18461539},
				{1.0,0.5966387,0.33333334,0.8},
				{1.0,0.17226891,0.2857143,0.12307692},
				{1.0,0.28991598,0.23809524,0.5692308},
				{1.0,0.57563025,0.1904762,0.64615387},
				{1.0,0.9285714,0.14285715,0.9230769},
				{1.0,0.5462185,0.0952381,0.7076923},
				{1.0,0.05042017,0.04761905,0.015384615},
				{1.0,0.394958,0.0,0.33846155},
				{1.0,0.394958,0.04761905,0.21538462},
				{1.0,0.3697479,0.0952381,0.4923077},
				{1.0,0.067226894,0.14285715,0.046153847},
				{1.0,0.57563025,0.1904762,0.35384616},
				{1.0,0.16386555,0.23809524,0.15384616},
				{1.0,0.016806724,0.2857143,0.046153847},
				{1.0,0.25630254,0.33333334,0.16923077},
				{1.0,0.6764706,0.3809524,0.46153846},
				{1.0,0.30252102,0.42857143,0.4923077},
				{1.0,0.28151262,0.47619048,0.18461539},
				{1.0,0.14705883,0.52380955,0.092307694},
				{1.0,0.31092438,0.5714286,0.2923077},
				{1.0,0.42016807,0.61904764,0.13846155},
				{1.0,0.075630255,0.6666667,0.03076923},
				{1.0,0.40756303,0.71428573,0.10769231},
				{1.0,0.15126051,0.7619048,0.12307692},
				{1.0,0.18907563,0.8095238,0.10769231},
				{1.0,0.25210086,0.85714287,0.10769231},
				{1.0,0.15546219,0.9047619,0.07692308},
				{1.0,0.4117647,0.95238096,0.15384616},
				{1.0,0.3487395,1.0,0.2}
				*/
/*
{1.0,0.55042017,1.0,0.30769232},
{1.0,0.85294116,0.95238096,0.35384616},
{1.0,0.15126051,0.9047619,0.07692308},
{1.0,1.0,0.85714287,1.0},
{1.0,0.6134454,0.8095238,0.5538462},
{1.0,0.20588236,0.7619048,0.06153846},
{1.0,0.13865547,0.71428573,0.13846155},
{1.0,0.6344538,0.6666667,0.07692308},
{1.0,0.13445379,0.61904764,0.06153846},
{1.0,0.34033614,0.5714286,0.12307692},
{1.0,0.13025211,0.52380955,0.16923077},
{1.0,0.24369748,0.47619048,0.2},
{1.0,0.071428575,0.42857143,0.06153846},
{1.0,0.13865547,0.3809524,0.18461539},
{1.0,0.5966387,0.33333334,0.8},
{1.0,0.17226891,0.2857143,0.12307692},
{1.0,0.28991598,0.23809524,0.5692308},
{1.0,0.57563025,0.1904762,0.64615387},
{1.0,0.9285714,0.14285715,0.9230769},
{1.0,0.5462185,0.0952381,0.7076923},
{1.0,0.05042017,0.04761905,0.015384615},
{1.0,0.394958,0.0,0.33846155},
{1.0,0.394958,0.04761905,0.21538462},
{1.0,0.3697479,0.0952381,0.4923077},
{1.0,0.067226894,0.14285715,0.046153847},
{1.0,0.57563025,0.1904762,0.35384616},
{1.0,0.16386555,0.23809524,0.15384616},
{1.0,0.016806724,0.2857143,0.046153847},
{1.0,0.25630254,0.33333334,0.16923077},
{1.0,0.6764706,0.3809524,0.46153846},
{1.0,0.30252102,0.42857143,0.4923077},
{1.0,0.28151262,0.47619048,0.18461539},
{1.0,0.14705883,0.52380955,0.092307694},
{1.0,0.31092438,0.5714286,0.2923077},
{1.0,0.42016807,0.61904764,0.13846155},
{1.0,0.075630255,0.6666667,0.03076923},
{1.0,0.40756303,0.71428573,0.10769231},
{1.0,0.15126051,0.7619048,0.12307692},
{1.0,0.18907563,0.8095238,0.10769231},
{1.0,0.25210086,0.85714287,0.10769231},
{1.0,0.15546219,0.9047619,0.07692308},
{1.0,0.4117647,0.95238096,0.15384616},
{1.0,0.3487395,1.0,0.2},
{1.0,0.5176471,1.0,0.13333334},
{1.0,0.5,0.90909094,0.093333334},
{1.0,0.6,0.8181818,0.41333333},
{1.0,0.24117647,0.72727275,0.093333334},
{1.0,0.24705882,0.6363636,0.06666667},
{1.0,0.84117645,0.54545456,0.61333334},
{1.0,0.21764706,0.45454547,0.17333333},
{1.0,0.30588236,0.36363637,0.24},
{1.0,0.27058825,0.27272728,0.26666668},
{1.0,0.20588236,0.18181819,0.18666667},
{1.0,1.0,0.09090909,1.0},
{1.0,0.14705883,0.0,0.08},
{1.0,0.27058825,0.09090909,0.29333332},
{1.0,0.44705883,0.18181819,0.16},
{1.0,0.5117647,0.27272728,0.16},
{1.0,0.16470589,0.36363637,0.21333334},
{1.0,0.20588236,0.45454547,0.13333334},
{1.0,0.23529412,0.54545456,0.093333334},
{1.0,0.45294118,0.6363636,0.29333332},
{1.0,0.5294118,0.72727275,0.36},
{1.0,0.4,0.8181818,0.30666667},
{1.0,0.28235295,0.90909094,0.08},
{1.0,0.20588236,1.0,0.10666667},
*/
/*
{1.0,0.55042017,1.0,0.1693600590820944,0.1693600590820944},
{1.0,0.85294116,0.95238096,0.3018099541719456,0.3018099541719456},
{1.0,0.15126051,0.9047619,0.0116354243115708,0.0116354243115708},
{1.0,1.0,0.85714287,1.0,1.0},
{1.0,0.6134454,0.8095238,0.33975440369748,0.33975440369748},
{1.0,0.20588236,0.7619048,0.0126696833755656,0.0126696833755656},
{1.0,0.13865547,0.71428573,0.0191984512921785,0.0191984512921785},
{1.0,0.6344538,0.6666667,0.048804140413704,0.048804140413704},
{1.0,0.13445379,0.61904764,0.0082740791777634,0.0082740791777634},
{1.0,0.34033614,0.5714286,0.0418875238758888,0.0418875238758888},
{1.0,0.13025211,0.52380955,0.022042664869424702,0.022042664869424702},
{1.0,0.24369748,0.47619048,0.048739496,0.048739496},
{1.0,0.071428575,0.42857143,0.0043956045054944996,0.0043956045054944996},
{1.0,0.13865547,0.3809524,0.025597933669683298,0.025597933669683298},
{1.0,0.5966387,0.33333334,0.47731096,0.47731096},
{1.0,0.17226891,0.2857143,0.0212023268545572,0.0212023268545572},
{1.0,0.28991598,0.23809524,0.165029105228184,0.165029105228184},
{1.0,0.57563025,0.1904762,0.37194571372656754,0.37194571372656754},
{1.0,0.9285714,0.14285715,0.85714280934066,0.85714280934066},
{1.0,0.5462185,0.0952381,0.3865546265675501,0.3865546265675501},
{1.0,0.05042017,0.04761905,7.7569490368455E-4,7.7569490368455E-4},
{1.0,0.394958,0.0,0.13367809686489998,0.13367809686489998},
{1.0,0.394958,0.04761905,0.08506787874595999,0.08506787874595999},
{1.0,0.3697479,0.0952381,0.18202973822883,0.18202973822883},
{1.0,0.067226894,0.14285715,0.0031027797799612175,0.0031027797799612175},
{1.0,0.57563025,0.1904762,0.20368455354234002,0.20368455354234002},
{1.0,0.16386555,0.23809524,0.025210085623788,0.025210085623788},
{1.0,0.016806724,0.2857143,7.756949680672279E-4,7.756949680672279E-4},
{1.0,0.25630254,0.33333334,0.0433742761971558,0.0433742761971558},
{1.0,0.6764706,0.3809524,0.312217198959276,0.312217198959276},
{1.0,0.30252102,0.42857143,0.148933427557854,0.148933427557854},
{1.0,0.28151262,0.47619048,0.051971562131221796,0.051971562131221796},
{1.0,0.14705883,0.52380955,0.01357466147963802,0.01357466147963802},
{1.0,0.31092438,0.5714286,0.090885590391726,0.090885590391726},
{1.0,0.42016807,0.61904764,0.058177122232708496,0.058177122232708496},
{1.0,0.075630255,0.6666667,0.00232708471105365,0.00232708471105365},
{1.0,0.40756303,0.71428573,0.043891404171299296,0.043891404171299296},
{1.0,0.15126051,0.7619048,0.0186166776884292,0.0186166776884292},
{1.0,0.18907563,0.8095238,0.0203619913594053,0.0203619913594053},
{1.0,0.25210086,0.85714287,0.0271493239663866,0.0271493239663866},
{1.0,0.15546219,0.9047619,0.011958630478345202,0.011958630478345202},
{1.0,0.4117647,0.95238096,0.063348417918552,0.063348417918552},
{1.0,0.3487395,1.0,0.0697479,0.0697479},
{1.0,0.5176471,1.0,0.069019616784314,0.069019616784314},
{1.0,0.5,0.90909094,0.046666667,0.046666667},
{1.0,0.6,0.8181818,0.247999998,0.247999998},
{1.0,0.24117647,0.72727275,0.02250980402745098,0.02250980402745098},
{1.0,0.24705882,0.6363636,0.0164705888235294,0.0164705888235294},
{1.0,0.84117645,0.54545456,0.515921561607843,0.515921561607843},
{1.0,0.21764706,0.45454547,0.0377254896745098,0.0377254896745098},
{1.0,0.30588236,0.36363637,0.07341176640000001,0.07341176640000001},
{1.0,0.27058825,0.27272728,0.07215687027451,0.07215687027451},
{1.0,0.20588236,0.18181819,0.0384313745529412,0.0384313745529412},
{1.0,1.0,0.09090909,1.0,1.0},
{1.0,0.14705883,0.0,0.0117647064,0.0117647064},
{1.0,0.27058825,0.09090909,0.07937254972548999,0.07937254972548999},
{1.0,0.44705883,0.18181819,0.07152941280000001,0.07152941280000001},
{1.0,0.5117647,0.27272728,0.08188235199999999,0.08188235199999999},
{1.0,0.16470589,0.36363637,0.0351372576313726,0.0351372576313726},
{1.0,0.20588236,0.45454547,0.027450982705882396,0.027450982705882396},
{1.0,0.23529412,0.54545456,0.02196078469019608,0.02196078469019608},
{1.0,0.45294118,0.6363636,0.1328627400941176,0.1328627400941176},
{1.0,0.5294118,0.72727275,0.190588248,0.190588248},
{1.0,0.4,0.8181818,0.12266666799999999,0.12266666799999999},
{1.0,0.28235295,0.90909094,0.022588236,0.022588236},
{1.0,0.20588236,1.0,0.0219607857529412,0.0219607857529412},
*/
{1.0,0.55042017,1.0,0.30769232,0.1693600590820944},
{1.0,0.85294116,0.95238096,0.35384616,0.3018099541719456},
{1.0,0.15126051,0.9047619,0.07692308,0.0116354243115708},
{1.0,1.0,0.85714287,1.0,1.0},
{1.0,0.6134454,0.8095238,0.5538462,0.33975440369748},
{1.0,0.20588236,0.7619048,0.06153846,0.0126696833755656},
{1.0,0.13865547,0.71428573,0.13846155,0.0191984512921785},
{1.0,0.6344538,0.6666667,0.07692308,0.048804140413704},
{1.0,0.13445379,0.61904764,0.06153846,0.0082740791777634},
{1.0,0.34033614,0.5714286,0.12307692,0.0418875238758888},
{1.0,0.13025211,0.52380955,0.16923077,0.022042664869424702},
{1.0,0.24369748,0.47619048,0.2,0.048739496},
{1.0,0.071428575,0.42857143,0.06153846,0.0043956045054944996},
{1.0,0.13865547,0.3809524,0.18461539,0.025597933669683298},
{1.0,0.5966387,0.33333334,0.8,0.47731096},
{1.0,0.17226891,0.2857143,0.12307692,0.0212023268545572},
{1.0,0.28991598,0.23809524,0.5692308,0.165029105228184},
{1.0,0.57563025,0.1904762,0.64615387,0.37194571372656754},
{1.0,0.9285714,0.14285715,0.9230769,0.85714280934066},
{1.0,0.5462185,0.0952381,0.7076923,0.3865546265675501},
{1.0,0.05042017,0.04761905,0.015384615,7.7569490368455E-4},
{1.0,0.394958,0.0,0.33846155,0.13367809686489998},
{1.0,0.394958,0.04761905,0.21538462,0.08506787874595999},
{1.0,0.3697479,0.0952381,0.4923077,0.18202973822883},
{1.0,0.067226894,0.14285715,0.046153847,0.0031027797799612175},
{1.0,0.57563025,0.1904762,0.35384616,0.20368455354234002},
{1.0,0.16386555,0.23809524,0.15384616,0.025210085623788},
{1.0,0.016806724,0.2857143,0.046153847,7.756949680672279E-4},
{1.0,0.25630254,0.33333334,0.16923077,0.0433742761971558},
{1.0,0.6764706,0.3809524,0.46153846,0.312217198959276},
{1.0,0.30252102,0.42857143,0.4923077,0.148933427557854},
{1.0,0.28151262,0.47619048,0.18461539,0.051971562131221796},
{1.0,0.14705883,0.52380955,0.092307694,0.01357466147963802},
{1.0,0.31092438,0.5714286,0.2923077,0.090885590391726},
{1.0,0.42016807,0.61904764,0.13846155,0.058177122232708496},
{1.0,0.075630255,0.6666667,0.03076923,0.00232708471105365},
{1.0,0.40756303,0.71428573,0.10769231,0.043891404171299296},
{1.0,0.15126051,0.7619048,0.12307692,0.0186166776884292},
{1.0,0.18907563,0.8095238,0.10769231,0.0203619913594053},
{1.0,0.25210086,0.85714287,0.10769231,0.0271493239663866},
{1.0,0.15546219,0.9047619,0.07692308,0.011958630478345202},
{1.0,0.4117647,0.95238096,0.15384616,0.063348417918552},
{1.0,0.3487395,1.0,0.2,0.0697479},
{1.0,0.5176471,1.0,0.13333334,0.069019616784314},
{1.0,0.5,0.90909094,0.093333334,0.046666667},
{1.0,0.6,0.8181818,0.41333333,0.247999998},
{1.0,0.24117647,0.72727275,0.093333334,0.02250980402745098},
{1.0,0.24705882,0.6363636,0.06666667,0.0164705888235294},
{1.0,0.84117645,0.54545456,0.61333334,0.515921561607843},
{1.0,0.21764706,0.45454547,0.17333333,0.0377254896745098},
{1.0,0.30588236,0.36363637,0.24,0.07341176640000001},
{1.0,0.27058825,0.27272728,0.26666668,0.07215687027451},
{1.0,0.20588236,0.18181819,0.18666667,0.0384313745529412},
{1.0,1.0,0.09090909,1.0,1.0},
{1.0,0.14705883,0.0,0.08,0.0117647064},
{1.0,0.27058825,0.09090909,0.29333332,0.07937254972548999},
{1.0,0.44705883,0.18181819,0.16,0.07152941280000001},
{1.0,0.5117647,0.27272728,0.16,0.08188235199999999},
{1.0,0.16470589,0.36363637,0.21333334,0.0351372576313726},
{1.0,0.20588236,0.45454547,0.13333334,0.027450982705882396},
{1.0,0.23529412,0.54545456,0.093333334,0.02196078469019608},
{1.0,0.45294118,0.6363636,0.29333332,0.1328627400941176},
{1.0,0.5294118,0.72727275,0.36,0.190588248},
{1.0,0.4,0.8181818,0.30666667,0.12266666799999999},
{1.0,0.28235295,0.90909094,0.08,0.022588236},
{1.0,0.20588236,1.0,0.10666667,0.0219607857529412}

/*
{1.0,0.55042017,1.0,0.30769232,0.1693600590820944},
{1.0,0.85294116,0.95238096,0.35384616,0.2874380538918336},
{1.0,0.15126051,0.9047619,0.07692308,0.010527288607442988},
{1.0,1.0,0.85714287,1.0,0.85714287},
{1.0,0.6134454,0.8095238,0.5538462,0.27503927594791805},
{1.0,0.20588236,0.7619048,0.06153846,0.009653092578323634},
{1.0,0.13865547,0.71428573,0.13846155,0.01371317979610316},
{1.0,0.6344538,0.6666667,0.07692308,0.03253609523594068},
{1.0,0.13445379,0.61904764,0.06153846,0.005122049188167574},
{1.0,0.34033614,0.5714286,0.12307692,0.02393572912586571},
{1.0,0.13025211,0.52380955,0.16923077,0.01154615836605416},
{1.0,0.24369748,0.47619048,0.2,0.02320928399519808},
{1.0,0.071428575,0.42857143,0.06153846,0.0018838305086342204},
{1.0,0.13865547,0.3809524,0.18461539,0.009751594266506661},
{1.0,0.5966387,0.33333334,0.8,0.1591036565154064},
{1.0,0.17226891,0.2857143,0.12307692,0.006057807975621011},
{1.0,0.28991598,0.23809524,0.5692308,0.03929264441628973},
{1.0,0.57563025,0.1904762,0.64615387,0.07084680615692442},
{1.0,0.9285714,0.14285715,0.9230769,0.12244897888540007},
{1.0,0.5462185,0.0952381,0.7076923,0.03681472818050299},
{1.0,0.05042017,0.04761905,0.015384615,3.6937854403299774E-5},
{1.0,0.394958,0.0,0.33846155,0.0},
{1.0,0.394958,0.04761905,0.21538462,0.004050851571397806},
{1.0,0.3697479,0.0952381,0.4923077,0.017336166412411137},
{1.0,0.067226894,0.14285715,0.046153847,4.4325427644288667E-4},
{1.0,0.57563025,0.1904762,0.35384616,0.038797059757441466},
{1.0,0.16386555,0.23809524,0.15384616,0.006002401387016355},
{1.0,0.016806724,0.2857143,0.046153847,2.2162714481485036E-4},
{1.0,0.25630254,0.33333334,0.16923077,0.014458092354880442},
{1.0,0.6764706,0.3809524,0.46153846,0.1189398912648137},
{1.0,0.30252102,0.42857143,0.4923077,0.06382861202327088},
{1.0,0.28151262,0.47619048,0.18461539,0.024748363117616332},
{1.0,0.14705883,0.52380955,0.092307694,0.007110537321051525},
{1.0,0.31092438,0.5714286,0.2923077,0.051934625677717436},
{1.0,0.42016807,0.61904764,0.13846155,0.03601441022014972},
{1.0,0.075630255,0.6666667,0.03076923,0.0015513898849385903},
{1.0,0.40756303,0.71428573,0.10769231,0.03135100366922156},
{1.0,0.15126051,0.7619048,0.12307692,0.014184136090867112},
{1.0,0.18907563,0.8095238,0.10769231,0.016483516620832944},
{1.0,0.25210086,0.85714287,0.10769231,0.02327084946310839},
{1.0,0.15546219,0.9047619,0.07692308,0.010819713232985514},
{1.0,0.4117647,0.95238096,0.15384616,0.060331827071751755},
{1.0,0.3487395,1.0,0.2,0.0697479},
{1.0,0.5176471,1.0,0.13333334,0.069019616784314},
{1.0,0.5,0.90909094,0.093333334,0.042424244169696985},
{1.0,0.6,0.8181818,0.41333333,0.20290908476363637},
{1.0,0.24117647,0.72727275,0.093333334,0.01637076707700535},
{1.0,0.24705882,0.6363636,0.06666667,0.010481283197860933},
{1.0,0.84117645,0.54545456,0.61333334,0.28141176838131887},
{1.0,0.21764706,0.45454547,0.17333333,0.017147950435080203},
{1.0,0.30588236,0.36363637,0.24,0.026695188248983967},
{1.0,0.27058825,0.27272728,0.26666668,0.019679146963279964},
{1.0,0.20588236,0.18181819,0.18666667,0.006987522960427828},
{1.0,1.0,0.09090909,1.0,0.09090909},
{1.0,0.14705883,0.0,0.08,0.0},
{1.0,0.27058825,0.09090909,0.29333332,0.007215686266524045},
{1.0,0.44705883,0.18181819,0.16,0.013005348367058831},
{1.0,0.5117647,0.27272728,0.16,0.02233155114096256},
{1.0,0.16470589,0.36363637,0.21333334,0.012777184816827129},
{1.0,0.20588236,0.45454547,0.13333334,0.012477719836007187},
{1.0,0.23529412,0.54545456,0.093333334,0.01197861015044564},
{1.0,0.45294118,0.6363636,0.29333332,0.08454901159215703},
{1.0,0.5294118,0.72727275,0.36,0.13860963924064199},
{1.0,0.4,0.8181818,0.30666667,0.1003636352242424},
{1.0,0.28235295,0.90909094,0.08,0.02053476069818184},
{1.0,0.20588236,1.0,0.10666667,0.0219607857529412},
*/
				/*
				{1.0,0.55042017,1.0,0.30769232,1.85811249},
				{1.0,0.85294116,0.95238096,0.35384616,2.1591682800000003},
				{1.0,0.15126051,0.9047619,0.07692308,1.13294549},
				{1.0,1.0,0.85714287,1.0,2.85714287},
				{1.0,0.6134454,0.8095238,0.5538462,1.9768154},
				{1.0,0.20588236,0.7619048,0.06153846,1.02932562},
				{1.0,0.13865547,0.71428573,0.13846155,0.99140275},
				{1.0,0.6344538,0.6666667,0.07692308,1.37804358},
				{1.0,0.13445379,0.61904764,0.06153846,0.81503989},
				{1.0,0.34033614,0.5714286,0.12307692,1.0348416599999999},
				{1.0,0.13025211,0.52380955,0.16923077,0.82329243},
				{1.0,0.24369748,0.47619048,0.2,0.9198879600000001},
				{1.0,0.071428575,0.42857143,0.06153846,0.5615384649999999},
				{1.0,0.13865547,0.3809524,0.18461539,0.70422326},
				{1.0,0.5966387,0.33333334,0.8,1.72997204},
				{1.0,0.17226891,0.2857143,0.12307692,0.58106013},
				{1.0,0.28991598,0.23809524,0.5692308,1.09724202},
				{1.0,0.57563025,0.1904762,0.64615387,1.4122603200000001},
				{1.0,0.9285714,0.14285715,0.9230769,1.9945054500000001},
				{1.0,0.5462185,0.0952381,0.7076923,1.3491489},
				{1.0,0.05042017,0.04761905,0.015384615,0.11342383500000001},
				{1.0,0.394958,0.0,0.33846155,0.73341955},
				{1.0,0.394958,0.04761905,0.21538462,0.6579616699999999},
				{1.0,0.3697479,0.0952381,0.4923077,0.9572937},
				{1.0,0.067226894,0.14285715,0.046153847,0.25623789099999994},
				{1.0,0.57563025,0.1904762,0.35384616,1.11995261},
				{1.0,0.16386555,0.23809524,0.15384616,0.55580695},
				{1.0,0.016806724,0.2857143,0.046153847,0.34867487099999994},
				{1.0,0.25630254,0.33333334,0.16923077,0.75886665},
				{1.0,0.6764706,0.3809524,0.46153846,1.5189614599999999},
				{1.0,0.30252102,0.42857143,0.4923077,1.22340015},
				{1.0,0.28151262,0.47619048,0.18461539,0.9423184900000001},
				{1.0,0.14705883,0.52380955,0.092307694,0.763176074},
				{1.0,0.31092438,0.5714286,0.2923077,1.17466068},
				{1.0,0.42016807,0.61904764,0.13846155,1.17767726},
				{1.0,0.075630255,0.6666667,0.03076923,0.7730661849999999},
				{1.0,0.40756303,0.71428573,0.10769231,1.22954107},
				{1.0,0.15126051,0.7619048,0.12307692,1.03624223},
				{1.0,0.18907563,0.8095238,0.10769231,1.10629174},
				{1.0,0.25210086,0.85714287,0.10769231,1.21693604},
				{1.0,0.15546219,0.9047619,0.07692308,1.13714717},
				{1.0,0.4117647,0.95238096,0.15384616,1.5179918200000002},
				{1.0,0.3487395,1.0,0.2,1.5487395},
				{1.0,0.5176471,1.0,0.13333334,1.65098044},
				{1.0,0.5,0.90909094,0.093333334,1.502424274},
				{1.0,0.6,0.8181818,0.41333333,1.8315151299999999},
				{1.0,0.24117647,0.72727275,0.093333334,1.061782554},
				{1.0,0.24705882,0.6363636,0.06666667,0.9500890900000001},
				{1.0,0.84117645,0.54545456,0.61333334,1.99996435},
				{1.0,0.21764706,0.45454547,0.17333333,0.84552586},
				{1.0,0.30588236,0.36363637,0.24,0.90951873},
				{1.0,0.27058825,0.27272728,0.26666668,0.80998221},
				{1.0,0.20588236,0.18181819,0.18666667,0.57436722},
				{1.0,1.0,0.09090909,1.0,2.0909090900000002},
				{1.0,0.14705883,0.0,0.08,0.22705883},
				{1.0,0.27058825,0.09090909,0.29333332,0.65483066},
				{1.0,0.44705883,0.18181819,0.16,0.78887702},
				{1.0,0.5117647,0.27272728,0.16,0.94449198},
				{1.0,0.16470589,0.36363637,0.21333334,0.7416756},
				{1.0,0.20588236,0.45454547,0.13333334,0.7937611699999999},
				{1.0,0.23529412,0.54545456,0.093333334,0.8740820140000001},
				{1.0,0.45294118,0.6363636,0.29333332,1.3826380999999999},
				{1.0,0.5294118,0.72727275,0.36,1.61668455},
				{1.0,0.4,0.8181818,0.30666667,1.52484847},
				{1.0,0.28235295,0.90909094,0.08,1.27144389},
				{1.0,0.20588236,1.0,0.10666667,1.31254903},
*/
/*				

				{1.0,0.55042017,1.0,0.30769232,0.0521107894943067},
				{1.0,0.85294116,0.95238096,0.35384616,0.10679429333351895},
				{1.0,0.15126051,0.9047619,0.07692308,8.950326751529056E-4},
				{1.0,1.0,0.85714287,1.0,1.0},
				{1.0,0.6134454,0.8095238,0.5538462,0.18817168542111523},
				{1.0,0.20588236,0.7619048,0.06153846,7.796728036199087E-4},
				{1.0,0.13865547,0.71428573,0.13846155,0.0026582473235145375},
				{1.0,0.6344538,0.6666667,0.07692308,0.003754164797374586},
				{1.0,0.13445379,0.61904764,0.06153846,5.091740905176259E-4},
				{1.0,0.34033614,0.5714286,0.12307692,0.005155387425070856},
				{1.0,0.13025211,0.52380955,0.16923077,0.003730297148704692},
				{1.0,0.24369748,0.47619048,0.2,0.0097478992},
				{1.0,0.071428575,0.42857143,0.06153846,2.7049873203719305E-4},
				{1.0,0.13865547,0.3809524,0.18461539,0.0047257725076227134},
				{1.0,0.5966387,0.33333334,0.8,0.381848768},
				{1.0,0.17226891,0.2857143,0.12307692,0.002609517086092188},
				{1.0,0.28991598,0.23809524,0.5692308,0.09393964959232337},
				{1.0,0.57563025,0.1904762,0.64615387,0.24033416235433375},
				{1.0,0.9285714,0.14285715,0.9230769,0.7912087273034675},
				{1.0,0.5462185,0.0952381,0.7076923,0.27356173275123064},
				{1.0,0.05042017,0.04761905,0.015384615,1.1933767450648884E-5},
				{1.0,0.394958,0.0,0.33846155,0.04524489586594419},
				{1.0,0.394958,0.04761905,0.21538462,0.018322312737904668},
				{1.0,0.3697479,0.0952381,0.4923077,0.08961464175903738},
				{1.0,0.067226894,0.14285715,0.046153847,1.4320522323902368E-4},
				{1.0,0.57563025,0.1904762,0.35384616,0.07207299712227141},
				{1.0,0.16386555,0.23809524,0.15384616,0.0038784748664909886},
				{1.0,0.016806724,0.2857143,0.046153847,3.580130687484472E-5},
				{1.0,0.25630254,0.33333334,0.16923077,0.007340262159037349},
				{1.0,0.6764706,0.3809524,0.46153846,0.14410024519317785},
				{1.0,0.30252102,0.42857143,0.4923077,0.07332107317412372},
				{1.0,0.28151262,0.47619048,0.18461539,0.009594750211764742},
				{1.0,0.14705883,0.52380955,0.092307694,0.0012530456980160136},
				{1.0,0.31092438,0.5714286,0.2923077,0.026566557890547526},
				{1.0,0.42016807,0.61904764,0.13846155,0.008055294518880278},
				{1.0,0.075630255,0.6666667,0.03076923,7.16026047038933E-5},
				{1.0,0.40756303,0.71428573,0.10769231,0.004726766704350857},
				{1.0,0.15126051,0.7619048,0.12307692,0.0022912833505245858},
				{1.0,0.18907563,0.8095238,0.10769231,0.002192829885694397},
				{1.0,0.25210086,0.85714287,0.10769231,0.0029237734128785354},
				{1.0,0.15546219,0.9047619,0.07692308,9.198946889761863E-4},
				{1.0,0.4117647,0.95238096,0.15384616,0.00974591083884442},
				{1.0,0.3487395,1.0,0.2,0.013949580000000001},
				{1.0,0.5176471,1.0,0.13333334,0.009202616031372644},
				{1.0,0.5,0.90909094,0.093333334,0.004355555617777778},
				{1.0,0.6,0.8181818,0.41333333,0.10250666501333335},
				{1.0,0.24117647,0.72727275,0.093333334,0.0021009150575686275},
				{1.0,0.24705882,0.6363636,0.06666667,0.0010980393098039226},
				{1.0,0.84117645,0.54545456,0.61333334,0.3164318945589541},
				{1.0,0.21764706,0.45454547,0.17333333,0.0065390847511634},
				{1.0,0.30588236,0.36363637,0.24,0.017618823936000002},
				{1.0,0.27058825,0.27272728,0.26666668,0.019241833035294267},
				{1.0,0.20588236,0.18181819,0.18666667,0.007173856711320272},
				{1.0,1.0,0.09090909,1.0,1.0},
				{1.0,0.14705883,0.0,0.08,9.41176512E-4},
				{1.0,0.27058825,0.09090909,0.29333332,0.02328261352784307},
				{1.0,0.44705883,0.18181819,0.16,0.011444706048000002},
				{1.0,0.5117647,0.27272728,0.16,0.01310117632},
				{1.0,0.16470589,0.36363637,0.21333334,0.007495948528941206},
				{1.0,0.20588236,0.45454547,0.13333334,0.0036601312104575373},
				{1.0,0.23529412,0.54545456,0.093333334,0.0020496732523921576},
				{1.0,0.45294118,0.6363636,0.29333332,0.03897306865610463},
				{1.0,0.5294118,0.72727275,0.36,0.06861176928},
				{1.0,0.4,0.8181818,0.30666667,0.037617778595555554},
				{1.0,0.28235295,0.90909094,0.08,0.0018070588800000002},
				{1.0,0.20588236,1.0,0.10666667,0.0023424838868496803},
*/
			};
		double thetas[][] = {
			//	{-2.7346184277846506},
			//	{0.024476504490013314},
			//	{0.02302979007734361},		
				{0},
				{0},
				{0},
				{0},
				{0},
				//{-7.054457763626635},
				//{4.2515750417828455},
				//{5.096226353828996},
				//{4.083813007993175},
		};
		double predict[][] = {
				{1},
				{0},	//{0},
				{1},
				{0},	//{0},
				{1},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},	//{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{1},
				{1},
				{0},
				{0},
				{0},
				{0},
				{0},
				{1},
				{0},
				{0},
				{0},
				{0},
				{1},	//{1},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{1},
				{1},
				{0},
				//DATA2
				{1},
				{0},
				{0},
				{0},
				{0},
				{1},
				{0},
				{0},
				{1},
				{0},
				{1},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{0},
				{1},
				{0},
				{0},
				{0},
							};
		
		/*
		for(int i=0;i<features.length;i++)
		{
			for(int j=0;j<features[0].length;j++)
			{
				features[i+1][j+1] = features[i][j];
				features[i][j] = 0;
				System.out.print(features[i][j]);
			}
		}
		*/
		double[][] data = {
				{1,1,1,1,1}
		};
		learner lr = new learner();
		double[][] tr = new double[4][];
		tr = lr.sigmoid(features,thetas);
		System.out.println(tr[0][0]);
		System.out.println();
		tr = lr.cost(features,thetas,predict);
		System.out.println(tr[0][0]);
		System.out.println();
		tr = lr.gradient(features,thetas,predict);
		tr = lr.parameter_update(features,thetas,predict);
		System.out.println(tr[0][0]);
		System.out.println(tr[1][0]);
		System.out.println(tr[2][0]);
		System.out.println(tr[3][0]);
		System.out.println(tr[4][0]);
		System.out.println();
		tr = lr.classify(data, thetas);
		System.out.println(tr[0][0]);
		
		//System.out.println(tr[3][0]);
	
	}
}