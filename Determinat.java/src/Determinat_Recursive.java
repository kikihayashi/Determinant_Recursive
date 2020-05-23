import java.util.ArrayList;
import java.util.List;

public class Determinat_Recursive {
	
	public static long determinant(int[][] matrix) 
	{
		int order = matrix.length;//n階

		if(order < 3) return (order==2)? det_sum(matrix) : (long)matrix[0][0];	
			
		List<int[][]> first_m = new ArrayList<>();
		List<int[]>   first_c = new ArrayList<>();
		
		first_m.add(matrix);//存n階矩陣(1個int[][])
		first_c.add(matrix[0]);//存n階矩陣係數(1個int[])
		
		return (int)get_det(first_m,first_c);//得到結果
	}

	public static long get_det(List<int[][]> m_list,List<int[]> c_list)//遞迴降階
	{				
		int order = m_list.get(0).length;//得到目前是幾階矩陣

		if(order == 2)//如果目前是2階
		{					
			long[] array2d_t = new long[m_list.size()];//建立一個陣列(大小=matrix_list大小)存放行列式結果

			for(int z = 0; z < array2d_t.length; z++)
			{
				array2d_t[z] = det_sum(m_list.get(z));//計算2階行列式
			}	
			return get_mc_plus(c_list, array2d_t, 3, c_list.size()-1);//得到結果
		}
		
		//如果目前不是2階，進行以下處理
		int[][] temp_m = new int[order-1][order-1];//建立一個n-1階矩陣
		List<int[][]> ans_m = new ArrayList<>();//負責存放n-1階矩陣	
		List<Integer> temp_c = new ArrayList<>();//負責存放n-1階矩陣係數
		
		for(int i = 0; i < m_list.size(); i++)//i = 0 ~ 目前m_list裡的int[][]數量
		{			
			int[][] matrix = m_list.get(i);//將m_list裡第i個int[][]存入matrix裡
		
			for(int j = 0; j < order; j++)//j = 0 ~ 目前matrix的階層(n)階
			{
				for(int k = 0; k < order-1; k++)//k = 0 ~ 目前temp_m的階層(n-1)階
				{
					for(int m = 0,n = 0; m < matrix.length; m++)//處理完可得到
					{
						if(m==j)continue;
						
						temp_m[k][n] = matrix[k+1][m];//將matrix元素依條件存到temp_m裡
						n++;				
					}											
				}
				
				//因為temp_m會被修改，所以事先複製一個copy_m，並加到ans_m裡
				int[][] copy_m = new int[temp_m.length][temp_m.length];
				
				for(int x = 0; x < temp_m.length; x++)
				{
					 for(int y = 0; y < temp_m[x].length; y++)
					 {
						 copy_m[x][y]=temp_m[x][y];
					 }					   
				}
				ans_m.add(copy_m);
		
				//以上可得到1個(n-1)階矩陣copy_m
				//以下將copy_m的矩陣係數存到temp_c裡，order=4時會得到copy_m(3階矩陣)與temp_c(2階係數)
		    	if(order>3)
		    	{
		    		int[] array_c = copy_m[0];
		    		for(int v = 0; v < array_c.length;v++)
		    		{	    			
		    			temp_c.add(array_c[v]);
		    		}
		    	}    	
			}
		}
		//以下將temp_c存入c_list裡
		if(order>3)
		{
			int[] array_cf = new int[temp_c.size()];
			for(int w = 0; w < array_cf.length; w++)
			{			
				array_cf[w] = temp_c.get(w);
			}			
			c_list.add(array_cf);
		}
		
		return get_det(ans_m, c_list);//使用遞迴語法，其中ans_m會更新(原始資料會變)、c_list會增加新資料(原始資料還在)
	}
	
	public static long det_sum(int[][] matrix_2d)//計算2階行列式
	{   
		return (long)(matrix_2d[0][0]*matrix_2d[1][1])-(matrix_2d[0][1]*matrix_2d[1][0]);
	}

	public static long get_mc_plus(List<int[]> c_list, long[] array_m, int order,int c_index)//使用遞迴語法計算出結果
	{   
		if(array_m.length==1)//如果array_m裡面只有1個值(即為答案)
		{			
			return array_m[0];
		}		
		//如果裡面還有多個值，進行以下處理
		int wantlength = c_list.get(c_index).length/order;//得到計算結果陣列長度
		long temp = 0;//用來存放行列式結果(第一次為3個2階矩陣計算總和)
		long[] ans = new long[wantlength];//用來存放行列式結果(陣列形式)
		
		for(int i = 0; i < wantlength; i++)//i = 0 ~ 目前行列式結果(陣列形式)
		{			
			for(int j = 0; j < order; j++)//j = 0 ~ order(從3開始往上到n階)
			{				
				temp += c_list.get(c_index)[(order*i)+j] * array_m[(order*i)+j] * Math.pow(-1, j);
			}
			ans[i] = temp;
			temp = 0;//重置temp
		}	
		order++;//從3開始+1(到n階)
		c_index--;//因為c_list.get(0)=第n階係數，所以c_index=c_list數量減1，遞減至0
		
		return get_mc_plus(c_list, ans, order, c_index);//使用遞迴語法計算出結果
	}

}
